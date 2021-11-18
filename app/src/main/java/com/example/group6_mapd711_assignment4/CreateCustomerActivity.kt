package com.example.group6_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class CreateCustomerActivity : AppCompatActivity() {


    lateinit var context: Context
    lateinit var strUserName: String
    lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customer)

        context = this@CreateCustomerActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel :: class.java)


        var btnCreateNewAccount  =findViewById<Button>(R.id.btnSave)
        var txtUserName = findViewById<EditText>(R.id.txtNewUserName)
         var strFirstName = findViewById<EditText>(R.id.txtFirstName)
        var strLastName = findViewById<EditText>(R.id.txtLastName)
        var strEmail = findViewById<EditText>(R.id.txtEmail)
        var strAddress = findViewById<EditText>(R.id.txtAddress)
        var strCity = findViewById<EditText>(R.id.txtCity)
        var strPhone = findViewById<EditText>(R.id.txtPhone)
        var strPostalCode= findViewById<EditText>(R.id.txtPostalCode)
        var strNewPassword = findViewById<EditText>(R.id.txtNewPassword)

        btnCreateNewAccount.setOnClickListener{
            //validation for the empty values
            if (txtUserName.text.toString().isEmpty()) {
                txtUserName.error = "Enter User Name"
                Toast.makeText( context,"User Name should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strFirstName.text.toString().trim().isEmpty()) {
                strFirstName.error = "Enter First Name"
                Toast.makeText( context,"First Name should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strLastName.text.toString().trim().isEmpty()) {
                strLastName.error = "Enter Last Name"
                Toast.makeText( context,"Last Name should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strEmail.text.toString().trim().isEmpty()) {
                strEmail.error = "Enter Email"
                Toast.makeText( context,"Email should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strAddress.text.toString().trim().isEmpty()) {
                strAddress.error = "Enter Address"
                Toast.makeText( context,"Adress should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strCity.text.toString().trim().isEmpty()) {
                strCity.error = "Enter City"
                Toast.makeText( context,"City should not be empty",Toast.LENGTH_LONG).show()
            }
            else  if (strPhone.text.toString().trim().isEmpty()) {
                strPhone.error = "Enter Phone"
                Toast.makeText( context,"Phone should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strPostalCode.text.toString().trim().isEmpty()) {
                strPostalCode.error = "Enter PostalCode"
                Toast.makeText( context,"PostalCode should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strNewPassword.text.toString().trim().isEmpty()) {
                strNewPassword.error = "Enter Password"
                Toast.makeText( context,"Password should not be empty",Toast.LENGTH_LONG).show()
            }
            else if (strNewPassword.text.toString().trim().length<6)
            {
                strNewPassword.error = "Password Length"
                Toast.makeText( context,"Password Length should not be more than 6 letters",Toast.LENGTH_LONG).show()
            }

            else {
                customerViewModel.getCustomer(context, txtUserName.text.toString().trim())!!.
                observe(this, Observer
                {

                    if (it == null) {
                        customerViewModel.insertCustomer(context, txtUserName.text.toString().trim(),strNewPassword.text.toString().trim(),strFirstName.text.toString().trim(),
                            strLastName.text.toString().trim(),strAddress.text.toString().trim(),strCity.text.toString().trim(),strPostalCode.text.toString().trim(),strPhone.text.toString().trim(),
                            strEmail.text.toString().trim())
                        Toast.makeText( context,"Your Account Has Been Successfully Created",Toast.LENGTH_LONG).show()
                        var intent = Intent(this, AddCustomerResultActivity::class.java)
                        val sharedPreferences : SharedPreferences = this.getSharedPreferences("UserProfile", 0)
                        val editor : SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("UserName",  txtUserName.text.toString().trim())
                        editor.putString("FirstName",  strFirstName.text.toString().trim())
                        editor.putString("LastName",  strLastName.text.toString().trim())
                        editor.commit()
                        startActivity(intent)

                    }
                    else {
                        Toast.makeText( context,"User Name Already Exist!!",Toast.LENGTH_LONG).show()

                    }
                })

            }
        }

    }



}