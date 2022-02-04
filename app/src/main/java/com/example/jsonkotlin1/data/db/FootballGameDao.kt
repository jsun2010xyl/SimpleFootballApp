package com.example.jsonkotlin1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonkotlin1.data.db.entity.FootballGame
import com.example.jsonkotlin1.data.db.entity.FootballTeam

@Dao
interface FootballGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertGames(gameListEntry: List<FootballGame>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertTeamStats(teamListEntry: List<FootballTeam>)

    // TODO
    @Query("select * from footballteams")
    fun getTeams(): LiveData<List<FootballTeam>>

    // TODO
    @Query("")
    fun getTeamDetails(): LiveData<List<FootballGame>>
}