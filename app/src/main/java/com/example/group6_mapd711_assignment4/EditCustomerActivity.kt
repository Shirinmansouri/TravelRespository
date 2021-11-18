package com.example.group6_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class EditCustomerActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_customer)

        context = this@EditCustomerActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel ::class.java)

        var txtUserName = findViewById<EditText>(R.id.txtNewUserNameEdit)
        var strFirstName = findViewById<EditText>(R.id.txtFirstNameEdit)
        var strLastName = findViewById<EditText>(R.id.txtLastNameEdit)
        var strEmail = findViewById<EditText>(R.id.txtEmailEdit)
        var strAddress = findViewById<EditText>(R.id.txtAddressEdit)
        var strCity = findViewById<EditText>(R.id.txtCityEdit)
        var strPhone = findViewById<EditText>(R.id.txtPhoneEdit)
        var strPostalCode= findViewById<EditText>(R.id.txtPostalCodeEdit)
        var strNewPassword = findViewById<EditText>(R.id.txtNewPasswordEdit)


        val shared = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val userName = shared.getString("UserName", "")
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        customerViewModel.getCustomer(context, userName!!)!!.observe(this
            , Observer {
                findViewById<TextView>(R.id.txtNewUserNameEdit).text =  it.userName
                findViewById<TextView>(R.id.txtFirstNameEdit).text =   it.firstName
                findViewById<TextView>(R.id.txtLastNameEdit).text =  it.lastName
                findViewById<TextView>(R.id.txtAddressEdit).text =  it.address
                findViewById<TextView>(R.id.txtEmailEdit).text =   it.email
                findViewById<TextView>(R.id.txtCityEdit).text =   it.city
                findViewById<TextView>(R.id.txtPostalCodeEdit).text =   it.postalCode
                findViewById<TextView>(R.id.txtPhoneEdit).text =   it.telephone
                findViewById<TextView>(R.id.txtNewPasswordEdit).text =   it.password
              //  Toast.makeText( context,"Your Account Has Been Successfully Created", Toast.LENGTH_LONG).show()


            })
        btnEdit.setOnClickListener{
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

                        customerViewModel.updateCustomer(context, txtUserName.text.toString().trim(),strNewPassword.text.toString().trim(),strFirstName.text.toString().trim(),
                            strLastName.text.toString().trim(),strAddress.text.toString().trim(),strCity.text.toString().trim(),strPostalCode.text.toString().trim(),strPhone.text.toString().trim(),
                            strEmail.text.toString().trim(),it.customerId)

                        var intent = Intent(this, EditCustomerActivity::class.java)
                        val sharedPreferences : SharedPreferences = this.getSharedPreferences("UserProfile", 0)
                        val editor : SharedPreferences.Editor = sharedPreferences.edit()
                      //  editor.putString("UserName",  txtUserName.text.toString().trim())
                        editor.putString("FirstName",  strFirstName.text.toString().trim())
                        editor.putString("LastName",  strLastName.text.toString().trim())
                        editor.commit()
                         Toast.makeText( context,"Information Successfully Updated",Toast.LENGTH_LONG).show()



                })

            }
        }

    }
}