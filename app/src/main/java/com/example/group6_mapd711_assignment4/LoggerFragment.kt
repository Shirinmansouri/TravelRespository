package com.example.group6_mapd711_assignment4

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.IOException

class LoggerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logger, container, false)
        val logScroll: TextView = view.findViewById(R.id.logTexView)
        val shared =  this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val username = shared?.getString("UserName", "")

        //check user admin
        if(username == "admin") {
            val filename = "Logger.txt"
            Thread(Runnable {
                try {
                    val input = requireContext().openFileInput(filename)
                    input.use {
                        var buffer = StringBuilder()
                        var bytes_read = input.read()

                        while (bytes_read != -1) {
                            buffer.append(bytes_read.toChar())
                            bytes_read = input.read()
                        }
                        requireActivity().runOnUiThread(Runnable {
                            logScroll.text = buffer.toString()
                        })
                    }

                } catch (fnfe: FileNotFoundException) {
                    fnfe.printStackTrace()
                } catch (ioe: IOException) {
                    ioe.printStackTrace()
                }
            }).start()
        }else{
            logScroll.text = "User doesn't have permission to see the logs!"
        }
        return view
    }

}