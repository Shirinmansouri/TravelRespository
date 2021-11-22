package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_package_detail.*


class PackageDetailFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_package_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnGoPayment = view.findViewById(R.id.btnGoPayment) as Button
        btnGoPayment.setOnClickListener{

            val spinnAdu = view.findViewById<Spinner>(R.id.spinnerAdult)
            val spinnKid = view.findViewById<Spinner>(R.id.spinnerKids)
            val numberOfAdults = spinnAdu.selectedItem.toString()
            val numberOfKids = spinnKid.selectedItem.toString()
            val numberOfSenior = spSenior.selectedItem.toString()


            val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
            val editor : SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putString("numberOfAdults", numberOfAdults)
            editor.putString("numberOfKids", numberOfKids)
            editor.putString("numberOfSenior",numberOfSenior)
            editor.commit()
            (activity as MainMenuActivity).goToConfirmFragment()
            }
    }

}