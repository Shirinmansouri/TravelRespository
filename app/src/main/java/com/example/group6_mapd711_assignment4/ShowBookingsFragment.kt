package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDate

class ShowBookingsFragment : Fragment(), AdapterView.OnItemClickListener {


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
        return inflater.inflate(R.layout.fragment_show_bookings, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnEditBooking = view.findViewById(R.id.btnEditABooking) as Button
        //val chosenDate = view.findViewById<DatePicker>(R.id.datePicker1)
        var listView = view.findViewById(R.id.listViewShowBookings) as ListView
        var itemList : ArrayList<String> = ArrayList()
        itemList.add("Bahamas")
        itemList.add("Caribbean")
        itemList.add("Cuba")
        itemList.add("Sampler")
        itemList.add("Star")
        //val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        //val editor : SharedPreferences.Editor? = sharedPreferences?.edit()

        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_single_choice, itemList)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener=this

        btnEditBooking.setOnClickListener {
                (activity as MainMenuActivity).goToEditBookings()
        }

    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        var item: String = parent?.getItemAtPosition(position) as String
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString("BookingId", item)
        editor?.commit()
    }

}