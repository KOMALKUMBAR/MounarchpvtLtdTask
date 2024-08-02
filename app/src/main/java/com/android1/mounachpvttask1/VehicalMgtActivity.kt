package com.android1.mounachpvttask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VehicalMgtActivity : AppCompatActivity() {
    lateinit var adapter: VehicalAdpter
    lateinit var recyclerView: RecyclerView
    private val dataList = mutableListOf<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehical_mgt)
        val vehicalName=findViewById<EditText>(R.id.vehicalName)
        val vehicalType=findViewById<EditText>(R.id.vehicleType)
        val regnum=findViewById<EditText>(R.id.regnum)
        val submit=findViewById<Button>(R.id.submit)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = VehicalAdpter(dataList)
        recyclerView.adapter = adapter
        submit.setOnClickListener {
            val text1 = vehicalName.text.toString()
            val text2 = vehicalType.text.toString()
            val text3 = regnum.text.toString()

            if (text1.isNotEmpty() && text2.isNotEmpty() && text3.isNotEmpty()) {
                val newItem = DataItem(text1, text2, text3)
                dataList.add(newItem)
                adapter.notifyDataSetChanged()

                // Clear the EditTexts
                vehicalName.text.clear()
                vehicalType.text.clear()
                regnum.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }




    }
}