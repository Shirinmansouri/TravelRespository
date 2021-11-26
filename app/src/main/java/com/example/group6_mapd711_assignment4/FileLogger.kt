package com.example.group6_mapd711_assignment4

import android.content.Context
import java.io.IOException

object FileLogger {

    //Singleton for activity log saveData

     fun saveData(strLog: String, applicationContext: Context) {
        val filename = "Logger.txt"
        Thread(Runnable {
            try {
                val out = applicationContext.openFileOutput(filename, Context.MODE_APPEND)
                out.use {
                    out.write(strLog.toByteArray())
                }
            }
            catch(ioe: IOException) {
                ioe.printStackTrace()
            }
        }).start()
    }
}