package com.zybooks.calendar_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class AddActivity : AppCompatActivity() {
    private lateinit var taskName: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

    }

    fun onClickFinish(view: View){
        val intent = Intent(this, FinishActivity::class.java)
        startActivity(intent)
    }
}