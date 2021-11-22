package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalTime
import kotlin.random.Random



class FinalBookingFragment : Fragment() {


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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var codeInText = ""
        for(i in 1..6)
        {
            val current = LocalTime.now().toSecondOfDay()
            var randomCode = Random(current+i).nextInt(0, 9)
            codeInText += randomCode.toString()
        }

        view.findViewById<TextView>(R.id.textViewCode).text = codeInText
    }
}