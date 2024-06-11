package com.example.bolachas.adapters

import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bolachas.R
import com.example.bolachas.activities.UpdateActivity
import com.example.novo.CreateDB

class CustomCursorAdapter(private var cursor: Cursor):
    RecyclerView.Adapter<CustomCursorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistTextView: TextView = itemView.findViewById(R.id.viewArtist)
        val recordTextView: TextView = itemView.findViewById(R.id.viewRecord)
        val shelfTextView: TextView = itemView.findViewById(R.id.viewShelf)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        val title = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ARTIST))
        val record = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.RECORD))
        val shelf = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SHELF))
        val id = cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ID))

        //holder.idTextView.text = id
        holder.artistTextView.text = title
        holder.recordTextView.text = record
        holder.shelfTextView.text = shelf

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("TAG_CODE", id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    fun swapCursor(newCursor: Cursor?) {
        if (newCursor != null) {
            cursor.close()
            cursor = newCursor
            notifyDataSetChanged()
        }
    }
}