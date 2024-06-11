package com.example.bolachas.activities

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bolachas.DatabaseController
import com.example.bolachas.R
import com.example.bolachas.adapters.CustomCursorAdapter

class ListDataActivity : ComponentActivity() {

    private lateinit var dbController: DatabaseController
    private lateinit var cursor: Cursor
    private lateinit var adapter: CustomCursorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data)

        dbController = DatabaseController(applicationContext)
        cursor = dbController.loadData()!!

        val recyclerView = findViewById<RecyclerView>(R.id.rvData)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomCursorAdapter(cursor)
        recyclerView.adapter = adapter

        val buttonCreate: ImageButton = findViewById(R.id.backButton)
        buttonCreate.setOnClickListener { onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        cursor = dbController.loadData()!!
        adapter.swapCursor(cursor)
    }


}