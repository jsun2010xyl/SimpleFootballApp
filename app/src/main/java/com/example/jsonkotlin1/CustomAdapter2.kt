package com.example.jsonkotlin1

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter2(private var teamInfo: Team): RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design2, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team = teamInfo.T[position]

        // sets the name to the textView1
        holder.textView1.text = team.name
        // sets the wins to the textView2
        holder.textView2.text = team.wins.toString()
        // sets the losses to the textView3
        holder.textView3.text = team.losses.toString()
        // sets the losses to the textView3
        holder.textView4.text = team.draws.toString()
        // sets the losses to the textView3
        holder.textView5.text = team.totalGame.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return teamInfo.T.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        // Used to show the name
        val textView1: TextView = itemView.findViewById(R.id.textView1Name)
        // Used to show wins
        val textView2: TextView = itemView.findViewById(R.id.textView2Wins)
        // Used to show losses
        val textView3: TextView = itemView.findViewById(R.id.textView3Losses)
        // Used to show draws
        val textView4: TextView = itemView.findViewById(R.id.textView4Draws)
        // Used to show winP
        val textView5: TextView = itemView.findViewById(R.id.textView5T)

    }

}