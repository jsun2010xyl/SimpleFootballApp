package com.example.jsonkotlin1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonkotlin1.R
import com.example.jsonkotlin1.Team

class CustomAdapter(private val mList: MutableList<Team>, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, onItemClicked)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val team = mList[position]

        // sets the name to the textView1
        holder.textView1.text = team.name
        // sets the wins to the textView2
        holder.textView2.text = team.wins.toString()
        // sets the losses to the textView3
        holder.textView3.text = team.losses.toString()
        // sets the losses to the textView3
        holder.textView4.text = team.draws.toString()
        // sets the losses to the textView3
        holder.textView5.text = "%.1f".format(team.winP)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        // Used to show the name
        val textView1: TextView = itemView.findViewById(R.id.textView1Name)
        // Used to show wins
        val textView2: TextView = itemView.findViewById(R.id.textView2Wins)
        // Used to show losses
        val textView3: TextView = itemView.findViewById(R.id.textView3Losses)
        // Used to show draws
        val textView4: TextView = itemView.findViewById(R.id.textView4Draws)
        // Used to show winP
        val textView5: TextView = itemView.findViewById(R.id.textView5WinP)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }
}