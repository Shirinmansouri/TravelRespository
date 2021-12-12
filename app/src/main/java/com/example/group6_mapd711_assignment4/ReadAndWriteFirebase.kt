package com.example.group6_mapd711_assignment4

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import java. util. UUID;
import android.R.string.no
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.security.AccessController.getContext
import com.google.firebase.database.FirebaseDatabase





abstract class ReadAndWriteFirebase {

    private lateinit var database: DatabaseReference
    // [START rtdb_write_new_log]
    fun writeNewLog(logDate: String, logString: String) {
        val _firebaseLog =FirebaseLog(logDate,logString)
        val uuid = UUID.randomUUID()
        database = Firebase.database.reference
        database.child("CruiseDatabase").child("Logs").child(uuid.toString()).setValue(_firebaseLog)
    }

}