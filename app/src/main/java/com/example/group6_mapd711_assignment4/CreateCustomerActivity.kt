package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class CreateCustomerActivity : AppCompatActivity() {


    lateinit var context: Context
    lateinit var strUserName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer)

        context = this@CreateCustomerActivity
        var btnCreateNewAccount  =findViewById<Button>(R.id.btnSave)
        var txtUserName = findViewById<EditText>(R.id.txtNewUserName)
        /*var strFirstName = findViewById<EditText>(R.id.txtFirstName).toString().trim()
        var strLastName = findViewById<EditText>(R.id.txtLastName).toString().trim()
        var strEmail = findViewById<EditText>(R.id.txtEmail).toString().trim()
        var strAddress = findViewById<EditText>(R.id.txtAddress).toString().trim()
        var strCity = findViewById<EditText>(R.id.txtCity).toString().trim()
        var strPhone = findViewById<EditText>(R.id.txtPhone).toString().trim()
        var strPostalCode= findViewById<EditText>(R.id.txtPostalCode).toString().trim()
        var strNewPassword = findViewById<EditText>(R.id.txtNewPassword).toString().trim()*/

        btnCreateNewAccount.setOnClickListener{


            strUserName = txtUserName.text.toString().trim()

            //validation for the empty values
            if (strUserName.isEmpty()) {
                txtUserName.error = "Enter User Name"
                Toast.makeText( context,"User Name should not be empty",Toast.LENGTH_LONG).show()
            }

        }

    }



}