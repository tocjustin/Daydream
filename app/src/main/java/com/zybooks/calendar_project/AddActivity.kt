package com.zybooks.calendar_project

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
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
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var year: Int = Calendar.YEAR
    private var month: Int = Calendar.MONTH
    private var dayOfMonth: Int = Calendar.DAY_OF_MONTH
    private var hour: Int = 0
    private var minute: Int = 0


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
            setTime(hourOfDay, minute)
            Toast.makeText(this, "Time changed to " + time, Toast.LENGTH_SHORT).show()
        }

        finishButton = findViewById<Button>(R.id.finish_button)
        taskName = findViewById<EditText>(R.id.reminder_name_edit_view)

        // Finish Button is visible when task name is given
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

        // Finish Button invisible until user inputs a name for task
        finishButton.visibility = View.GONE

        //Sets up alarm manager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun onClickFinish(view: View){
        setAlarm()
        Toast.makeText(this, "Time : " + time + "Date: " + date, Toast.LENGTH_LONG).show()
    }

    private fun setTime(hours: Int, mins: Int) {
        hour = hours
        minute = mins
        time = "$hours:$mins"
    }

    private fun setAlarm() {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val currentTime = Calendar.getInstance()

        if (calendar.before(currentTime)) {
            // If the alarm time is in the past, don't save alarm.
            return
        }

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}
