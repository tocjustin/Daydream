package com.zybooks.calendar_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import java.util.Calendar
import java.util.Date

class AddActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private lateinit var timePicker: TimePicker
    private lateinit var date: String
    private lateinit var time: String
    private lateinit var finishButton: Button
    private lateinit var taskName: EditText
    var year: Int = Calendar.YEAR
    var month: Int = Calendar.MONTH
    var dayOfMonth: Int = Calendar.DAY_OF_MONTH
    var hour: Int = 0
    var minute: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        calendarView = findViewById(R.id.calendarView)
        calendarView
            .setOnDateChangeListener { view, year, month, dayOfMonth ->
                // In this Listener we are getting values
                // such as year, month and day of month
                // on below line we are saved in date variable.
                date = (month + 1).toString() + "/" +
                        (dayOfMonth.toString() + "/" + year.toString())
                this.year = year.toInt()
                this.month = (month + 1).toInt()
                this.dayOfMonth = dayOfMonth.toInt()
                Toast.makeText(this, "Date changed to " + date, Toast.LENGTH_SHORT).show()
            }

        timePicker = findViewById<TimePicker>(R.id.timePicker)
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            time = "$hourOfDay:$minute"
        }

        finishButton = findViewById<Button>(R.id.finish_button)
        taskName = findViewById<EditText>(R.id.reminder_name_edit_view)
        taskName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            // Button is visible when user inputs something for taskName
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty())
                    finishButton.visibility = View.VISIBLE
                else
                    finishButton.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable) {}
        }
        )
        finishButton.visibility = View.GONE
    }

    fun onClickFinish(view: View){
        setTime(timePicker.hour, timePicker.minute)

        val saveData = SaveData(applicationContext)
        saveData.setAlarm(year, month, dayOfMonth, hour, minute)

        val intent = Intent(this, FinishActivity::class.java)
        startActivity(intent)
    }

    fun setTime(hours: Int, mins: Int) {
        hour = hours
        minute = mins
        time = "$hours:$mins"
    }
}
