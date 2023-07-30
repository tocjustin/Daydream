package com.zybooks.calendar_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

class FinishActivity : AppCompatActivity() {
    private lateinit var taskName: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        taskName = findViewById(R.id.reminder_name_edit_view)
        nextButton = findViewById(R.id.finish_button)
        nextButton.visibility = View.GONE

        taskName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            // Button is visible when user inputs something for taskName
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty())
                    nextButton.visibility = View.VISIBLE
                else
                    nextButton.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {}
        }
        )
    }
}