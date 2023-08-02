package com.zybooks.calendar_project

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.DayOfWeek
import java.time.Month
import java.util.Calendar

class SaveData {
    var context:Context?=null
    constructor(context:Context){
        this.context = context
    }
    fun setAlarm(year: Int, month: Int, dayOfMonth: Int,
                 hour: Int, minute:Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth, hour, minute, 0)

        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(context, MyBroadcastReceiver::class.java)
        intent.putExtra("message", "Alarm time")
        intent.action = "com.tester.alarmmanager"
        val pi = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
    }
}
