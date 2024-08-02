package com.android1.mounachpvttask1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ResigterPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        val userName = findViewById<TextView>(R.id.Username)
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)
        val mobile=findViewById<TextView>(R.id.mobile)
        val btnRegister = findViewById<Button>(R.id.register)
        val backToLogin = findViewById<TextView>(R.id.back)

        btnRegister.setOnClickListener {
            val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
            val newUser: String = userName.getText().toString()
            val newPassword: String = password.getText().toString()
            val newEmail: String = email.getText().toString()
            val newmbl: String = mobile.getText().toString()
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString(newUser + newPassword + "data",newUser + "\n" + newEmail)
            editor.commit()
            //FORM VALIDATION CODE USER AND PASSEORD EMAIL
            if (newUser.isEmpty()) {
                userName .error = "Enter name"
                return@setOnClickListener }

            //Password Validation format
            if (newPassword.isEmpty()) {
                password.error = "Enter password"
                return@setOnClickListener
            } //Validation
            else if (newPassword.filter { it.isDigit() }.firstOrNull() == null)
            else if (newPassword.filter { it.isLetter() }.filter { it.isUpperCase() }
                    .firstOrNull() == null)
            else if (newPassword.filter { it.isLetter() }.filter { it.isLowerCase() }
                    .firstOrNull() == null) {
                return@setOnClickListener
            } else if (newPassword.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {
                return@setOnClickListener
            }
            else if(newEmail .isEmpty())
            {
                email.error="Enter Email"
                return@setOnClickListener
            }
            if (newmbl.isEmpty()) {
                mobile.error = "Enter mobile numbar"
                return@setOnClickListener
            }
            else {
                // Go to the main page
                val intent = Intent(this, LoginPage::class.java)
                startActivity(intent)
                finish() // Close the login activity
            }



        }

        backToLogin.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent) }
    }


}


