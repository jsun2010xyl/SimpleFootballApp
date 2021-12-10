package com.example.jsonkotlin1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: MutableList<Item>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the listId to the textView1 from our itemHolder class
        holder.textView1.text = ItemsViewModel.listId.toString()
        // sets the name to the textView2 from our itemHolder class
        holder.textView2.text = ItemsViewModel.name
        // sets the id to the textView1 from our itemHolder class
        holder.textView3.text = ItemsViewModel.id.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        // Used to show listId
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        // Used to show name
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        // Used to show id
        val textView3: TextView = itemView.findViewById(R.id.textView3)
    }
}