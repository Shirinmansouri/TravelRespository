package com.example.group6_mapd711_assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun createNewCustomer(view: View)
    {
        val intent= Intent(this,CreateCustomerActivity::class.java)
startActivity(intent)
    }
}