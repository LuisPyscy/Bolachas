package com.example.bolachas.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.bolachas.DatabaseController
import com.example.bolachas.R
import java.util.Calendar

class RegisterActivity : ComponentActivity() {
    private lateinit var artistEditText: EditText
    private lateinit var recordEditText: EditText
    private lateinit var yearEditText: EditText
    private lateinit var styleEditText: EditText
    private lateinit var shelfEditText: EditText
    private lateinit var registerButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initializeViews()

        registerButton.setOnClickListener {
            val artistString = artistEditText.text.toString()
            val recorString = recordEditText.text.toString()
            val yearString = yearEditText.text.toString()
            val styleString = styleEditText.text.toString()
            val shelfString = shelfEditText.text.toString()

            val emptyFields = mutableListOf<String>()
            if (artistString.isEmpty()) emptyFields.add("Artista")
            if (recorString.isEmpty()) emptyFields.add("Gravadora")
            if (yearString.isEmpty()) emptyFields.add("Ano")
            if (styleString.isEmpty()) emptyFields.add("Estilo")
            if (shelfString.isEmpty()) emptyFields.add("Prateleira")

            if (emptyFields.isNotEmpty()) {
                val message = "Por favor, preencha os seguintes campos: ${emptyFields.joinToString()}"
                showToast(message)
            }else {
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                if (yearString.toInt() > currentYear) {
                    showToast("Ano inválido. O ano não pode ser maior que o ano atual.")
                } else {
                    val dbController = DatabaseController(applicationContext)
                    val result = dbController.insertData(
                        artistString,
                        recorString,
                        yearString,
                        styleString,
                        shelfString
                    )
                    showToast(result)

                    clearViews()

                    navigateToListDataActivity()
                }
            }
        }
        val buttonCreate: ImageButton = findViewById(R.id.backButton)
        buttonCreate.setOnClickListener { onBackPressed() }
    }

    private fun initializeViews() {
        artistEditText = findViewById(R.id.etArtist)
        recordEditText = findViewById(R.id.etRecord)
        yearEditText = findViewById(R.id.etYear)
        styleEditText = findViewById(R.id.etStyle)
        shelfEditText = findViewById(R.id.etShelf)
        registerButton = findViewById(R.id.buttonRegister)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun clearViews() {
        artistEditText.text.clear()
        recordEditText.text.clear()
        yearEditText.text.clear()
        styleEditText.text.clear()
        shelfEditText.text.clear()
    }

    private fun navigateToListDataActivity() {
        val intent = Intent(this, ListDataActivity::class.java)
        startActivity(intent)
    }
}