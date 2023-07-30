package com.zybooks.calendar_project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {
    private var toDoList = TaskList(this)
    private lateinit var listTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listTextView = findViewById(R.id.item_list)
        toDoList.addTask("Study for Algebra exam")
        toDoList.addTask("Wash the car")
        toDoList.addTask("Volunteer at the hospital")
        displayList()
    }

    fun onClickAdd(view: View){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }


    private fun displayList() {

        // Display a numbered list of items
        val itemText = StringBuffer()
        val items = toDoList.getTask()
        val lineSeparator = System.getProperty("line.separator")

        for (i in items.indices) {
            itemText.append(i + 1).append(". ").append(items[i]).append(lineSeparator)
        }

        listTextView.text = itemText.toString()
    }
}