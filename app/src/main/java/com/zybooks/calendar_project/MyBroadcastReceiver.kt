package com.zybooks.calendar_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.action.equals("com.tester.alarmmanager")) {
                var extra = intent.extras
                Toast.makeText(context, extra!!.getString("message"), Toast.LENGTH_LONG).show()
            }
        }
    }
}
