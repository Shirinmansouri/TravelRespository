package com.example.group6_mapd711_assignment4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton

class PaymentOptionsFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_payment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val payCash = view.findViewById<RadioButton>(R.id.radioButtonCash)
        val payCredit = view.findViewById<RadioButton>(R.id.radioButtonCredit)
        val payDebit = view.findViewById<RadioButton>(R.id.radioButtonDebit)
        var btnGoPayment = view.findViewById<Button>(R.id.btnGoPayment)
        btnGoPayment.setOnClickListener{
            if(payCash.isChecked) {
                (activity as MainMenuActivity).goToFinalBookingFragment()
            }
            else if(payCredit.isChecked){
                (activity as MainMenuActivity).goToPaymentFragment()
            }
            else if(payDebit.isChecked){
                (activity as MainMenuActivity).goToPaymentFragment()
            }
        }

    }


}