package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class EditBookingFragment : Fragment() {

    lateinit var bookingViewModel: BookingViewModel

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
        return inflater.inflate(R.layout.fragment_edit_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingViewModel = ViewModelProvider(this).get(BookingViewModel ::class.java)

        var txtNumberOfAdults = view.findViewById(R.id.txtNewNumberOfAdults) as EditText
        var txtNewNumberOfKids = view.findViewById(R.id.txtNewNumberOfKids) as EditText
        var txtNewNumberOfSeniors = view.findViewById(R.id.txtNewNumberOfSeniors) as EditText
        var txtNewDate = view.findViewById(R.id.txtNewDate) as EditText
        var txtAmountPaid = view.findViewById(R.id.txtAmountPaid) as EditText
        var txtCustomerId = view.findViewById(R.id.txtCustomerId) as EditText
        var txtCruiseCode = view.findViewById(R.id.txtCruiseCode) as EditText
        var txtBookingCode = view.findViewById(R.id.txtBookingCode) as EditText


        val shared = this.activity?.getSharedPreferences("BookingProfile", AppCompatActivity.MODE_PRIVATE)
        val bookingId = shared?.getString("bookingID", "")
        val btnEditBooking = view.findViewById(R.id.btnEditBooking) as Button
        bookingViewModel.getBookings(requireContext(), bookingId!!.toInt())!!.observe(viewLifecycleOwner
            , Observer {
                ( view.findViewById(R.id.txtNewNumberOfAdults) as TextView).text =  it.numberOfAdults
                ( view.findViewById(R.id.txtNewNumberOfKids) as TextView).text =   it.numberOfKids
                ( view.findViewById(R.id.txtNewNumberOfSeniors) as TextView).text =  it.numberOfSeniors
                ( view.findViewById(R.id.txtNewDate) as TextView).text =  it.startDate
                ( view.findViewById(R.id.txtAmountPaid) as TextView).text  =   it.amountPaid
                ( view.findViewById(R.id.txtCustomerId) as TextView).text  =   it.customerId.toString()
                ( view.findViewById(R.id.txtCruiseCode) as TextView).text  =   it.cruiseCode.toString()
                ( view.findViewById(R.id.txtBookingCode) as TextView).text =   it.bookingId.toString()

            })
        btnEditBooking.setOnClickListener{
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

            }
        }

    }

}