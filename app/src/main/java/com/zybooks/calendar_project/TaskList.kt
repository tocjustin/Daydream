package com.zybooks.calendar_project

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.Collections

const val FILENAME = "todolist.txt"

class TaskList (var context: Context) {
    private var listOfTasks: MutableList<String> = mutableListOf()

    fun addTask(task: String) {
        listOfTasks.add(task)
    }

    fun deleteTask(taskId: Unit) {
        //TODO: Delete specific task
    }

    fun getTask(): List<String> {
        return Collections.unmodifiableList(listOfTasks)
    }

    fun saveToFile() {
        // Write list to file in internal storage
        val outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
        writeListToStream(outputStream)
    }

    fun readFromFile() {
        try {
            // Read in list from file in internal storage
            val inputStream: FileInputStream = context.openFileInput(FILENAME)
            val reader = inputStream.bufferedReader()
            listOfTasks.clear()
            reader.forEachLine { listOfTasks.add(it) }
        } catch (ex: FileNotFoundException) {
            // Ignore
        }
    }

    private fun writeListToStream(outputStream: FileOutputStream) {
        val writer = PrintWriter(outputStream)
        for (item in listOfTasks) {
            writer.println(item)
        }
        writer.close()
    }
}