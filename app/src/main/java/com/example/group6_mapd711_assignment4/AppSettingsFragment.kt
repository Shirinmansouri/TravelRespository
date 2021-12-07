package com.example.group6_mapd711_assignment4

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi

class AppSettingsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_app_settings, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val musicSwitch = view.findViewById<Switch>(R.id.switchForMusic)
        val lightMusic = view.findViewById<RadioButton>(R.id.radioButtonLightMusic)
        val wildmusic = view.findViewById<RadioButton>(R.id.radioButtonWildMusic)
        val applySettings = view.findViewById<Button>(R.id.buttonApplySettings)

        musicSwitch.isChecked = true
        lightMusic.isEnabled = true
        wildmusic.isEnabled = true



        musicSwitch.setOnClickListener {
            if (!musicSwitch.isChecked) {
                lightMusic.isEnabled = false
                wildmusic.isEnabled = false
            } else if (musicSwitch.isChecked) {
                lightMusic.isEnabled = true
                wildmusic.isEnabled = true
            }
        }

        applySettings.setOnClickListener {
            if(!musicSwitch.isChecked){
                (activity as MainMenuActivity).shutUpMusic()
            }
            else{
                if ((!lightMusic.isChecked)&&(!wildmusic.isChecked)){
                    Toast.makeText(this.context, "Choose one type of music!", Toast.LENGTH_SHORT).show()
                }
                else if(lightMusic.isChecked){
                    (activity as MainMenuActivity).turnOnLightMusic()
                }
                else if(wildmusic.isChecked){
                    (activity as MainMenuActivity).turnOnWildMusic()
                }
            }
        }
                /*
        btnGoPayment.setOnClickListener{
            if(payCash1.isChecked) {
                (activity as MainMenuActivity).goToFinalBookingFragment()
            }
            else if(payCredit1.isChecked){
                (activity as MainMenuActivity).goToPaymentFragment()
            }
            else if(payDebit1.isChecked){
                (activity as MainMenuActivity).goToPaymentFragment()
            }
        }*/

    }

}