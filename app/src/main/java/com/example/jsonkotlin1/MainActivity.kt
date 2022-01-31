package com.example.jsonkotlin1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Use it to store the data from the json file
    private val teams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the List to our Adapter
        val adapter = CustomAdapter(teams) { position -> onListItemClick(position) }
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}