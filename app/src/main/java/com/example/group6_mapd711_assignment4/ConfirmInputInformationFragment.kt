package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


import kotlin.math.roundToInt


class ConfirmInputInformationFragment : Fragment() {



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
        return inflater.inflate(R.layout.fragment_confirm_input_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val shared = this.activity?. getSharedPreferences("BookingProfile", AppCompatActivity.MODE_PRIVATE)
        var cruiseCode : Int = shared?.getString("CruiseCode", "")!!.toInt()

        var cruiseName : String = shared?.getString("cruiseName", "")!!
        var duration : String = shared?.getString("duration", "")!!
        var price : String = shared?.getString("price", "")!!.replace("$","")
        var visitingPlaces : String = shared?.getString("visitingPlaces", "")!!

        val dataAdults = shared?.getString("numberOfAdults", "")
        val dataKids = shared?.getString("numberOfKids", "")
        val dataSenior= shared?.getString("numberOfSenior","")
        var finalPrice1 = (price?.let { dataAdults?.toFloat()?.times(it.toFloat()) })
        var finalPrice2 = price?.toFloat()?.let {
            dataKids?.toFloat()
                ?.times(it.div(2))
        }
        var finalPrice = finalPrice2?.let { finalPrice1?.plus(it) }
        var finalPriceWithTax = (finalPrice?.times(1.13))?.roundToInt()


        view.findViewById<TextView>(R.id.textViewPayment).text ="Cruise Name: " + cruiseName +
                "\n\nVisiting Places: " + visitingPlaces +
                "\n\nNumber of nights: " + duration +
                "\n" + "\nNumber of Adults: " + dataAdults +
                "\n" + "\nNumber of Kids: " + dataKids +
                "\n" + "\nNumber of Seniors: " + dataSenior +
                "\n" + "\nAmount to pay (tax included):$ " + finalPriceWithTax

        val editor : SharedPreferences.Editor = shared.edit()
        editor.putString("payableAmount", finalPriceWithTax.toString())
        editor.commit()
        var btnGoToPayment = view.findViewById<Button>(R.id.btnGoToPaymentOption)
        btnGoToPayment.setOnClickListener {
            (activity as MainMenuActivity).goToPaymentTypeFragment()
        }
        }

    }

