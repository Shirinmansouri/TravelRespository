package com.example.group6_mapd711_assignment4

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate

class ShowBookingsFragment : Fragment(), AdapterView.OnItemClickListener {


    lateinit var bookingViewModel: BookingViewModel
    var arrayList: ArrayList<BookingModel> = ArrayList()
    var arrayListInString: ArrayList<String> = ArrayList()

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
        bookingViewModel = ViewModelProvider(this).get(BookingViewModel ::class.java)

        val shared = this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val customerId = shared?.getString("customerId", "")

        /*
        bookingViewModel.getBookingsWithNothing(requireContext())!!.observe(viewLifecycleOwner
            , Observer { it ->

                for (cruise in it)
                {
                    arrayList.add(cruise)

                }

                var listView = view.findViewById(R.id.listViewShowBookings) as ListView
                //var adapter = ArrayAdapter<BookingModel>(requireContext(), android.R.layout.simple_list_item_single_choice, arrayList)
                var adapter = ListViewShowBookingsAdapter(requireContext(), arrayList)
                listView.adapter = adapter
                listView.choiceMode = ListView.CHOICE_MODE_SINGLE
                listView.onItemClickListener=this
            })
        */
        bookingViewModel.getBookingsByCustomer(requireContext(), customerId.toString().toInt())!!.observe(viewLifecycleOwner
            , Observer { it ->

                for (cruise in it)
                {
                    arrayList.add(cruise)

                }

                var listView = view.findViewById(R.id.listViewShowBookings) as ListView
                //var adapter = ArrayAdapter<BookingModel>(requireContext(), android.R.layout.simple_list_item_single_choice, arrayList)
                var adapter = ListViewShowBookingsAdapter(requireContext(), arrayList)
                listView.adapter = adapter
                listView.choiceMode = ListView.CHOICE_MODE_SINGLE
                listView.onItemClickListener=this
            })

        /*
        btnEditBooking.setOnClickListener {
                (activity as MainMenuActivity).goToEditBookings()
        }*/

    }



    override fun onItemClick(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

        val bookingID = p1!!.findViewById<TextView>(R.id.bookingId).text.toString()
        val amountPaid = p1!!.findViewById<TextView>(R.id.amountPaid).text.toString()
        val startDate = p1!!.findViewById<TextView>(R.id.startDate).text.toString()


        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString("bookingID", bookingID)
        editor?.putString("amountPaid",amountPaid)
        editor?.putString("startDate",startDate)

        editor?.commit()
        (activity as MainMenuActivity).goToEditBookings()

    }

}