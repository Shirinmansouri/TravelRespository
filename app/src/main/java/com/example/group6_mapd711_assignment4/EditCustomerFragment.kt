package com.example.group6_mapd711_assignment4


import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.time.LocalTime
import java.util.*

class EditCustomerFragment : Fragment() {




    lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_customer, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerViewModel = ViewModelProvider(this).get(CustomerViewModel ::class.java)

        var txtUserName = view.findViewById(R.id.txtNewUserNameEdit) as EditText
        var strFirstName = view.findViewById(R.id.txtFirstNameEdit) as EditText
        var strLastName = view.findViewById(R.id.txtLastNameEdit) as EditText
        var strEmail = view.findViewById(R.id.txtEmailEdit) as EditText
        var strAddress = view.findViewById(R.id.txtAddressEdit) as EditText
        var strCity = view.findViewById(R.id.txtCityEdit) as EditText
        var strPhone = view.findViewById(R.id.txtPhoneEdit) as EditText
        var strPostalCode= view.findViewById(R.id.txtPostalCodeEdit) as EditText
        var strNewPassword = view.findViewById(R.id.txtNewPasswordEdit) as EditText


        val shared = this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val userName = shared?.getString("UserName", "")
        val btnEdit = view.findViewById(R.id.btnEdit) as Button
        customerViewModel.getCustomer(requireContext(), userName!!)!!.observe(viewLifecycleOwner
            , Observer {
                ( view.findViewById(R.id.txtNewUserNameEdit) as TextView).text =  it.userName
                ( view.findViewById(R.id.txtFirstNameEdit) as TextView).text =   it.firstName
                ( view.findViewById(R.id.txtLastNameEdit) as TextView).text =  it.lastName
                ( view.findViewById(R.id.txtAddressEdit) as TextView).text =  it.address
                ( view.findViewById(R.id.txtEmailEdit) as TextView).text  =   it.email
                ( view.findViewById(R.id.txtCityEdit) as TextView).text  =   it.city
                ( view.findViewById(R.id.txtPostalCodeEdit) as TextView).text  =   it.postalCode
                ( view.findViewById(R.id.txtPhoneEdit) as TextView).text =   it.telephone
                ( view.findViewById(R.id.txtNewPasswordEdit) as TextView).text =   it.password
                //  Toast.makeText( context,"Your Account Has Been Successfully Created", Toast.LENGTH_LONG).show()


            })
        btnEdit.setOnClickListener{
            //validation for the empty values
            if (txtUserName.text.toString().isEmpty()) {
                txtUserName.error = "Enter User Name"
                Toast.makeText( context,"User Name should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strFirstName.text.toString().trim().isEmpty()) {
                strFirstName.error = "Enter First Name"
                Toast.makeText( context,"First Name should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strLastName.text.toString().trim().isEmpty()) {
                strLastName.error = "Enter Last Name"
                Toast.makeText( context,"Last Name should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strEmail.text.toString().trim().isEmpty()) {
                strEmail.error = "Enter Email"
                Toast.makeText( context,"Email should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strAddress.text.toString().trim().isEmpty()) {
                strAddress.error = "Enter Address"
                Toast.makeText( context,"Adress should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strCity.text.toString().trim().isEmpty()) {
                strCity.error = "Enter City"
                Toast.makeText( context,"City should not be empty", Toast.LENGTH_LONG).show()
            }
            else  if (strPhone.text.toString().trim().isEmpty()) {
                strPhone.error = "Enter Phone"
                Toast.makeText( context,"Phone should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strPostalCode.text.toString().trim().isEmpty()) {
                strPostalCode.error = "Enter PostalCode"
                Toast.makeText( context,"PostalCode should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strNewPassword.text.toString().trim().isEmpty()) {
                strNewPassword.error = "Enter Password"
                Toast.makeText( context,"Password should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (strNewPassword.text.toString().trim().length<6)
            {
                strNewPassword.error = "Password Length"
                Toast.makeText( context,"Password Length should not be more than 6 letters",
                    Toast.LENGTH_LONG).show()
            }

            else {
                customerViewModel.getCustomer(requireContext(), txtUserName.text.toString().trim())!!.
                observe(viewLifecycleOwner, Observer
                {

                    customerViewModel.updateCustomer(requireContext(), txtUserName.text.toString().trim(),strNewPassword.text.toString().trim(),strFirstName.text.toString().trim(),
                        strLastName.text.toString().trim(),strAddress.text.toString().trim(),strCity.text.toString().trim(),strPostalCode.text.toString().trim(),strPhone.text.toString().trim(),
                        strEmail.text.toString().trim(),it.customerId)

                    val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("UserProfile", 0)
                    val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
                    editor?.putString("FirstName",  strFirstName.text.toString().trim())
                    editor?.putString("LastName",  strLastName.text.toString().trim())
                    editor?.putString("Email",   strEmail.text.toString().trim())
                    editor?.commit()
                    Toast.makeText( context,"Information Successfully Updated", Toast.LENGTH_LONG).show()
                })

                //log update customer
                var logStr = LocalTime.now().toString() + " UPDATE CUSTOMER=> Username:" + txtUserName.text.toString().trim() +
                        " First Name:" + strFirstName.text.toString().trim() + " Last Name:" + strLastName.text.toString().trim() +
                        " Address:" + strAddress.text.toString().trim() + " City:" + strCity.text.toString().trim() +
                        " Postal Code:" + strPostalCode.text.toString().trim() + " Phone:" + strPhone.text.toString().trim() +
                        " Email:" + strEmail.text.toString().trim() +"\n"
                FileLogger.saveData(logStr, requireContext().applicationContext)
                var variableName = object: ReadAndWriteFirebase(){}
                variableName.writeNewLog(LocalTime.now().toString() ,logStr)

            }
        }
    }
}