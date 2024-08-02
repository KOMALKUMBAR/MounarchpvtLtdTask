package com.android1.mounachpvttask1

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

class HomePage : AppCompatActivity() {
    lateinit var cardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        //SharedPreferences  calling menthod
        val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val display: String? = preferences.getString("display", "")
        val displayInfo = findViewById<TextView>(R.id.display)
        displayInfo.text = display

       //homescreen activity
        val vehical = findViewById<CardView>(R.id.vehical)
        val rotiM=findViewById<CardView>(R.id.Rotine)
        val audio =findViewById<CardView>(R.id.audio)
        val trip=findViewById<CardView>(R.id.tripMgn)
        //use intent to call the particular block of mangement in TMS
       vehical .setOnClickListener() {
            val i = Intent(this, VehicalMgtActivity::class.java)
            startActivity(i) }
        rotiM.setOnClickListener() {
            val intent1 = Intent(this, RoutiMAngemnt::class.java)
            startActivity(intent1) }
        audio.setOnClickListener() {
            val intent2 = Intent(this, AudioProcessActivity::class.java)
            startActivity(intent2) }
        trip.setOnClickListener() {
            val intent3 = Intent(this, tripmangement::class.java)
            startActivity(intent3) }
    }
}