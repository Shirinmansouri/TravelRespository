package com.example.group6_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var customerViewModel: CustomerViewModel
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        context = this@MainActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel :: class.java)


        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var txtUserName = findViewById<EditText>(R.id.txtUserName)
            var txtPassword = findViewById<EditText>(R.id.txtPassword)

            if (txtUserName.text.toString().trim().isEmpty() || txtPassword.text.toString().trim().isEmpty())
            {
                Toast.makeText( context,"UserName and Password Should Not be Empty", Toast.LENGTH_LONG).show()
            }
            else
            {
            customerViewModel.getCustomer(context , txtUserName.text.toString().trim())!!.observe(this , Observer {
                if(it == null)
                {
                    Toast.makeText( context,"UserName Not Found", Toast.LENGTH_LONG).show()
                }
                else if (it.password.equals(txtPassword.text.toString().trim()))
                {
                    var intent = Intent(this, MainMenuActivity::class.java)
                    val sharedPreferences : SharedPreferences = this.getSharedPreferences("UserProfile", 0)
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("UserName",  it.userName)
                    editor.putString("FirstName",   it.firstName)
                    editor.putString("LastName",   it.lastName)
                    editor.putString("Email",   it.email)
                    editor.commit()
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText( context,"Password Is Incorrect", Toast.LENGTH_LONG).show()
                }

            } )

        }
        }


    }
    fun createNewCustomer(view: View)
    {
        val intent= Intent(this,CreateCustomerActivity::class.java)
         startActivity(intent)
    }
}