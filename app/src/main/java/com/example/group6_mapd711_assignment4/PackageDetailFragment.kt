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
            val radioYes = view.findViewById<RadioButton>(R.id.radioButtonYes)
            val radioNo = view.findViewById<RadioButton>(R.id.radioButtonNo)


            val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
            val editor : SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putString("numberOfAdults", numberOfAdults)
            editor.putString("numberOfKids", numberOfKids)
            if(radioYes.isChecked) {
                editor.putString("Over60", "YES")
            }
            else if(radioNo.isChecked){
                editor.putString("Over60", "NO")
            }
            editor.commit()
            (activity as MainMenuActivity).goToConfirmFragment()
            }
    }

}