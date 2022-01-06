package com.example.jsonkotlin1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    // Use it to store the data from the json file
    private val teams = mutableListOf<Team>()

    private val latch = CountDownLatch(1)

    //private val adapter1 = CustomAdapter(teams) { position -> onListItemClick(position) }

    private fun onListItemClick(position: Int) {

        data1.team = teams[position]

        val intent = Intent(this, TeamDetail1::class.java).apply {
        }
        startActivity(intent)
    }

    private fun getTeamIndex(teamName: String): Int{
        for (i in teams.indices){
            if (teams[i].name == teamName){
                return i
            }
        }
        return -1
    }

    private fun addTeamToList(teamName: String){
        teams.add(Team(teamName, 0, 0, 0, 0.0f, mutableListOf()))
    }

    private fun updatePlayingData(T: MutableList<TotalGame>, opponentName:String, type:Int){

        var teamIndex = -1 // the opponent team's index
        for (i in T.indices){
            if (T[i].name == opponentName){
                teamIndex = i
                break
            }
        }

        if (teamIndex == -1){ // if there is no record of the two teams playing each other
            T.add(TotalGame(opponentName, 0, 0, 0, 1))
            teamIndex = T.size - 1
        } else {
            T[teamIndex].totalGame++
        }

        if (type == 0) { // 0: win
            T[teamIndex].wins++
        }else if (type == 1){ // 1: loss
            T[teamIndex].losses++
        }else if (type == 2){ // 2: draw
            T[teamIndex].draws++
        }
    }

    private fun getTeamStandings(){

        thread(start=true) {

            // Use try-catch to handle exceptions
            try {
                // Get the json file
                val connection1 =
                    URL("https://s.yimg.com/cv/ae/default/171221/soccer_game_results.json").openConnection() as HttpURLConnection
                val data1 = connection1.inputStream.bufferedReader().readText()
                // Parse the json file
                val jsonArray1 = JSONTokener(data1).nextValue() as JSONArray
                var homeTeam: String
                var awayTeam: String
                var homeScore: Int
                var awayScore: Int
                var homeTeamIndex = -1
                var awayTeamIndex = -1
                // Store the data in the list
                for (i in 0 until jsonArray1.length()) {
                    homeTeam = jsonArray1.getJSONObject(i).getString("homeTeamName")
                    awayTeam = jsonArray1.getJSONObject(i).getString("awayTeamName")
                    homeScore = jsonArray1.getJSONObject(i).getInt("homeScore")
                    awayScore = jsonArray1.getJSONObject(i).getInt("awayScore")

                    homeTeamIndex = getTeamIndex(homeTeam)
                    if (homeTeamIndex == -1) {
                        addTeamToList(homeTeam)
                        homeTeamIndex = teams.size - 1
                    }

                    awayTeamIndex = getTeamIndex(awayTeam)
                    if (awayTeamIndex == -1) {
                        addTeamToList(awayTeam)
                        awayTeamIndex = teams.size - 1
                    }

                    // Update the record of the teams playing each other
                    if (homeScore > awayScore) {
                        teams[homeTeamIndex].wins++
                        teams[awayTeamIndex].losses++
                        updatePlayingData(teams[homeTeamIndex].T, awayTeam, 0)
                        updatePlayingData(teams[awayTeamIndex].T, homeTeam, 1)
                    } else if (homeScore == awayScore) {
                        teams[homeTeamIndex].draws++
                        teams[awayTeamIndex].draws++
                        updatePlayingData(teams[homeTeamIndex].T, awayTeam, 2)
                        updatePlayingData(teams[awayTeamIndex].T, homeTeam, 2)
                    } else {
                        teams[homeTeamIndex].losses++
                        teams[awayTeamIndex].wins++
                        updatePlayingData(teams[homeTeamIndex].T, awayTeam, 1)
                        updatePlayingData(teams[awayTeamIndex].T, homeTeam, 0)
                    }
                }
                // Cal the winning percentage
                for (i in teams.indices) {
                    teams[i].winP =
                        100 * teams[i].wins.toFloat() / (teams[i].wins + teams[i].losses + teams[i].draws).toFloat()
                }
                teams.sortByDescending { it.winP }

                latch.countDown()

            } catch (e: Exception) {
                // Record the exception information
                Log.i("Exception msg", e.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getTeamStandings()

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        latch.await()
        // This will pass the List to our Adapter
        val adapter = CustomAdapter(teams) { position -> onListItemClick(position) }
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}