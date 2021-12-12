package com.example.group6_mapd711_assignment4

import android.annotation.SuppressLint
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.internal.ViewUtils.getContentView
import java.time.LocalTime
import java.util.*
import kotlin.random.Random



class FinalBookingFragment : Fragment() {

    lateinit var customerViewModel: CustomerViewModel
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
        return inflater.inflate(R.layout.fragment_final_booking, container, false)

    }

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shared = this.activity?. getSharedPreferences("BookingProfile", AppCompatActivity.MODE_PRIVATE)
        val sharedProfile = this.activity?. getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)

        var cruiseCode : Int = shared?.getString("CruiseCode", "")!!.toInt()
        var amountPaid= shared?.getString("payableAmount", "")
        val dataAdults = shared?.getString("numberOfAdults", "")
        val dataKids = shared?.getString("numberOfKids", "")
        val dataSenior = shared?.getString("numberOfSenior","")
        val startDate = shared?.getString("StartDate" , "")
        val temp = sharedProfile?.getString("customerId" , "").toString()
        var customerId : Int = temp.toInt()
        //= shared?.getString("customerId" , "").toString().toInt()
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel ::class.java)
        bookingViewModel = ViewModelProvider(this).get(BookingViewModel ::class.java)
        cruiseViewModel = ViewModelProvider(this).get(CruiseViewModel ::class.java)
        /*customerViewModel.getCustomer(requireContext(), sharedProfile?.getString("UserName","")!!)!!.observe(viewLifecycleOwner
           , Observer {
               customerId = it.customerId!!
           })*/
        var variableName = object: ReadAndWriteFirebase(){}
        var cruiseName : String =""
        bookingViewModel.insertBooking(requireContext(),dataAdults.toString(),dataKids.toString(),dataSenior.toString(),amountPaid.toString() ,startDate.toString(),
            customerId,cruiseCode)



        var codeInText = ""
        for(i in 1..6)
        {
            val current = LocalTime.now().toSecondOfDay()
            var randomCode = Random(current+i).nextInt(0, 9)
            codeInText += randomCode.toString()
        }

        view.findViewById<TextView>(R.id.textViewCode).text = codeInText

        //log insert booking
        var logStr = LocalTime.now().toString() + " INSERT BOOKING=>  NumOfAdults:" + dataAdults.toString() +
                " NumOfKids:" + dataKids.toString() + " NumOfSeniors:" + dataSenior.toString() + " $:" + amountPaid.toString() +
                " Cruise date:" + startDate.toString() + " Customer Id:" + customerId.toString() + " Cruise Code:" + cruiseCode.toString() +  "\n"
        FileLogger.saveData(logStr, requireContext().applicationContext)

        variableName.writeNewLog(LocalTime.now().toString() ,logStr)


    }

}