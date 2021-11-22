package com.example.group6_mapd711_assignment4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class CreditPayFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_credit_pay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var btnPayment = view.findViewById<Button>(R.id.buttonCreditPayNow)
        btnPayment.setOnClickListener{
            val cardNumber = view.findViewById<EditText>(R.id.cardNumber).text
            var cardNumberFirstDigit = cardNumber.toString().toCharArray()[0]
            val cVV = view.findViewById<EditText>(R.id.cVV).text.toString().toCharArray()
            val expiryDate = view.findViewById<EditText>(R.id.expiryDate).text

            if((cardNumberFirstDigit.compareTo('4') == 0) || (cardNumberFirstDigit.compareTo('2') == 0) || (cardNumberFirstDigit.compareTo('5') == 0) || (cardNumberFirstDigit.compareTo('3') == 0)) {
                if(cVV.size == 3) {
                    (activity as MainMenuActivity).goToFinalBookingFragment()
                }
                else{
                    Toast.makeText( context,"Error_Message\", \"CVV has to have just three digits!", Toast.LENGTH_LONG).show()
                }
            }
            else{

                Toast.makeText( context,"Error_Message\", \"The entered number is not valid!\\n\" +\n" +
                        "                        \"It is neither Visa, nor master card, nor American Express!", Toast.LENGTH_LONG).show()
            }
        }
    }
}