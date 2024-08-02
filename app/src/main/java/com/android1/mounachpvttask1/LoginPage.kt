package com.android1.mounachpvttask1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.textfield.TextInputEditText

class LoginPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val Username = findViewById<TextInputEditText>(R.id.Username)
        val password = findViewById<TextInputEditText>(R.id.password)
        val btnRegister = findViewById<TextView>(R.id.registerMe)
        val button = findViewById<Button>(R.id.Login)

        //validation form
        button.setOnClickListener() {
            val name = Username.text.toString()
            val Password = password.text.toString()
            val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
            val userDetails: String? = preferences.getString(name + Password + "data", "Username or Password is Incorrect.")
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("display", userDetails)
            editor.commit()
            if (name.isEmpty()) {
                Username.error = "Enter name"
                return@setOnClickListener
            }

            //Password Validation format
            if (Password.isEmpty()) {
                password.error = "Enter password"
                return@setOnClickListener
            } //Validation
            else if (Password.filter { it.isDigit() }.firstOrNull() == null)
            else if (Password.filter { it.isLetter() }.filter { it.isUpperCase() }
                    .firstOrNull() == null)
            else if (Password.filter { it.isLetter() }.filter { it.isLowerCase() }
                    .firstOrNull() == null) {
                return@setOnClickListener
            } else if (Password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {
                return@setOnClickListener
            } else {
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            }


        }


        //resigter page
        btnRegister.setOnClickListener {
            Toast.makeText(this, "done", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ResigterPage::class.java)
            startActivity(intent)
        }


    }
}








