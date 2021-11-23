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
import kotlin.math.roundToInt

class EditBookingFragment : Fragment() {

    lateinit var bookingViewModel: BookingViewModel
    lateinit var cruiseViewModel: CruiseViewModel

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
        cruiseViewModel = ViewModelProvider(this).get(CruiseViewModel ::class.java)

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
            if (txtNumberOfAdults.text.toString().isEmpty()) {
                txtNumberOfAdults.error = "Enter Number of Adults"
                Toast.makeText( context,"Number of Adults should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (txtNewNumberOfKids.text.toString().trim().isEmpty()) {
                txtNewNumberOfKids.error = "Enter Number of Kids"
                Toast.makeText( context,"Number of Kids should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (txtNewNumberOfSeniors.text.toString().trim().isEmpty()) {
                txtNewNumberOfSeniors.error = "Enter Number of Seniors"
                Toast.makeText( context,"Number of Seniors should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (txtNewDate.text.toString().trim().isEmpty()) {
                txtNewDate.error = "Enter a new date"
                Toast.makeText( context,"Date should not be empty", Toast.LENGTH_LONG).show()
            }

            else {
                /*
                var price : String = ""
                cruiseViewModel.getCruises(requireContext(), txtCruiseCode.text.toString().toInt())!!.observe(viewLifecycleOwner,
                {
                    price = it.price
                })

                var finalPrice1 = (price?.let { (txtNumberOfAdults.text.toString())?.toFloat()?.times(it.toFloat()) })
                var finalPrice2 = price?.toFloat()?.let {
                    (txtNewNumberOfKids.text.toString())?.toFloat()
                        ?.times(it.div(2))
                }
                var finalPrice = finalPrice2?.let { finalPrice1?.plus(it) }
                var finalPriceWithTax = (finalPrice?.times(1.13))?.roundToInt().toString()
                */
                    bookingViewModel.updateBooking(requireContext(), txtNumberOfAdults.text.toString().trim(),txtNewNumberOfKids.text.toString().trim(),txtNewNumberOfSeniors.text.toString().trim(),
                        txtAmountPaid.text.toString().trim(),txtNewDate.text.toString().trim(),txtCustomerId.text.toString().trim().toInt(),txtCruiseCode.text.toString().trim().toInt(),txtBookingCode.text.toString().trim().toInt())

                    Toast.makeText( context,"Information Successfully Updated", Toast.LENGTH_LONG).show()

            }
        }

    }

}