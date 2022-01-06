package com.example.jsonkotlin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TeamDetail1 : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    // This will pass the List to our Adapter
    val adapter1 = data1.team?.let { CustomAdapter2(it) }

    private fun sortT(T: MutableList<TotalGame>, type: Int){
        // TODO : 注意正序倒序问题
        if ( type == 0 ){ // Sort by wins
            T.sortBy { it.wins }
        }else if ( type == 1 ){ // Sort by losses
            T.sortBy { it.losses }
        }else if ( type == 2 ){ // Sort by draws
            T.sortBy { it.draws }
        }else if (type == 3){ // Sort by T
            T.sortBy { it.totalGame }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail1)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerviewTeam)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        val textViewTeamName: TextView = findViewById(R.id.textViewTeamName) as TextView

        if (data1.team != null) {
            textViewTeamName.text = data1.team!!.name
        }


        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter1

        val spinner: Spinner = findViewById(R.id.spinner_sort)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.sort_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        data1.team?.let { sortT(it.T, pos) }
        if (adapter1 != null) {
            adapter1.notifyDataSetChanged()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}