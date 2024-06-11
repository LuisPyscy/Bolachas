package com.example.bolachas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.bolachas.activities.ListDataActivity
import com.example.bolachas.activities.RegisterActivity


class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCreate: Button = findViewById(R.id.btadd)
        //val buttonUpdate: Button = findViewById(R.id.buttonUpd)
        val buttonList: Button = findViewById(R.id.btlib)

        buttonCreate.setOnClickListener {
            startNewActivity(RegisterActivity::class.java)
        }

       /* buttonUpdate.setOnClickListener {
            startNewActivity(ListDataActivity::class.java)
        }*/

        buttonList.setOnClickListener {
            startNewActivity(ListDataActivity::class.java)
        }
    }

    private fun startNewActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
