package com.example.group6_mapd711_assignment4

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import java. util. UUID;
import android.R.string.no




abstract class ReadAndWriteFirebase {

    private lateinit var database: DatabaseReference
    // [START rtdb_write_new_log]
    fun writeNewLog(logDate: String, logString: String) {
        val _firebaseLog =FirebaseLog(logDate,logString)
        val uuid = UUID.randomUUID()
        database = Firebase.database.reference
        database.child("CruiseDatabase").child("Logs").child(uuid.toString()).setValue(_firebaseLog)
    }
    // [END rtdb_write_new_log]
}