package com.android1.mounachpvttask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class tripmangement : AppCompatActivity(){
    lateinit var adapter: TripAdpter
    lateinit var recyclerView: RecyclerView
    private val dataList = mutableListOf<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tripmangement)
        val destinationEditText=findViewById<EditText>(R.id.destinationEditText)
        val startDateEditText=findViewById<EditText>(R.id.startDateEditText)
        val endDateEditText=findViewById<EditText>(R.id.endDateEditText)
        val saveButton=findViewById<Button>(R.id.saveButton)
        val RCView=findViewById<RecyclerView>(R.id.RCView)

        val layoutManager = LinearLayoutManager(this)
        RCView.layoutManager = layoutManager

        adapter = TripAdpter(dataList)
        RCView.adapter = adapter
        saveButton.setOnClickListener {
            val newdestinationEditText = destinationEditText.text.toString()
            val newstartDateEditText = startDateEditText.text.toString()
            val newendDateEditText = endDateEditText.text.toString()

            if (newdestinationEditText.isNotEmpty() && newstartDateEditText.isNotEmpty() && newendDateEditText.isNotEmpty()) {
                val newItem = DataItem(newdestinationEditText, newstartDateEditText, newendDateEditText)
                dataList.add(newItem)
                adapter.notifyDataSetChanged()

                // Clear the EditTexts
                destinationEditText.text.clear()
                startDateEditText.text.clear()
                endDateEditText.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


