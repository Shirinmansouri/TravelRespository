package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class AddCustomerResultActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer_result)
        context = this@AddCustomerResultActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel ::class.java)

        val shared = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val userName = shared.getString("UserName", "")
val btnReturn = findViewById<Button>(R.id.btnReturn)
        customerViewModel.getCustomer(context, userName!!)!!.observe(this
        , Observer {
            findViewById<TextView>(R.id.lblUserName3).text = "UserName: " + it.userName
            findViewById<TextView>(R.id.lblFirstName3).text = "FirstName: " + it.firstName
                findViewById<TextView>(R.id.lblLastName3).text = "LastName: " + it.lastName
                findViewById<TextView>(R.id.lblAddress3).text = "Address: " + it.address
                findViewById<TextView>(R.id.lblEmail3).text = "Email: " + it.email
                findViewById<TextView>(R.id.lblCity3).text = "City: " + it.city
                findViewById<TextView>(R.id.lblPostalCode3).text = "PostalCode: " + it.postalCode
                findViewById<TextView>(R.id.lblCellPhone3).text = "Phone: " + it.telephone


            })


    }
}