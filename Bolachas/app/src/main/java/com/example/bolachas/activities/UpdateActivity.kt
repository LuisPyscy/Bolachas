package com.example.bolachas.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.bolachas.DatabaseController
import com.example.bolachas.R
import com.example.novo.CreateDB
import java.util.Calendar

class UpdateActivity : ComponentActivity() {
    private lateinit var id: String
    private lateinit var artist: EditText
    private lateinit var record: EditText
    private lateinit var year: EditText
    private lateinit var style: EditText
    private lateinit var shelf: EditText
    private lateinit var updateButton: ImageButton
    private lateinit var deleteButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        id = intent.getStringExtra("TAG_CODE").toString()
        initializeViews()

        val dbController = DatabaseController(applicationContext)
        val cursor = dbController.loadDataById(id.toInt())

        cursor?.apply {
            artist.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.ARTIST)))
            record.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.RECORD)))
            year.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.YEAR)))
            style.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.STYLE)))
            shelf.setText(cursor.getString(cursor.getColumnIndexOrThrow(CreateDB.SHELF)))
        }

        updateButton.setOnClickListener {
            if(validateInputs()) {
                dbController.updateData(
                    id.toInt(),
                    artist.text.toString(),
                    record.text.toString(),
                    year.text.toString(),
                    style.text.toString(),
                    shelf.text.toString()
                )
                finish()
            }
        }

        deleteButton.setOnClickListener {
            dbController.deleteData(id.toInt())
            finish()
        }



    }

    private fun initializeViews() {
        artist = findViewById(R.id.etUpdateArtist)
        record = findViewById(R.id.etUpdateRecord)
        year = findViewById(R.id.etUpdateYear)
        style = findViewById(R.id.etUpdateStyle)
        shelf = findViewById(R.id.etUpdateShelf)
        updateButton = findViewById(R.id.btUpdateUpdate)
        deleteButton = findViewById(R.id.btUpdateDelete)
    }

    //Exception to Check Empty Values
    private fun validateInputs(): Boolean {
        if (artist.text.toString().isEmpty()) {
            showToast("O campo 'Artista' não pode estar vazio.")
            return false
        }
        if (record.text.toString().isEmpty()) {
            showToast("O campo 'Gravadora' não pode estar vazio.")
            return false
        }
        if (year.text.toString().isEmpty()) {
            showToast("O campo 'Ano' não pode estar vazio.")
            return false
        }
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        if (year.text.toString().toInt() > currentYear) {
            showToast("Ano inválido. O ano não pode ser maior que o ano atual.")
            return false
        }
        if (style.text.toString().isEmpty()) {
            showToast("O campo 'Estilo' não pode estar vazio.")
            return false
        }
        if (shelf.text.toString().isEmpty()) {
            showToast("O campo 'Prateleira' não pode estar vazio.")
            return false
        }
        return true
    }
    //Print Mensage
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
