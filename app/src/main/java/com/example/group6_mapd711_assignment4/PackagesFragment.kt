package com.example.group6_mapd711_assignment4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class PackagesFragment : Fragment(), AdapterView.OnItemClickListener {

    lateinit var cruiseViewModel: CruiseViewModel
    var arrayList: ArrayList<CruiseModel> = ArrayList()
    var adapter: ListViewAdapter? = null


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
        return inflater.inflate(R.layout.fragment_packages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cruiseViewModel = ViewModelProvider(this).get(CruiseViewModel ::class.java)

        val shared = this.activity?.getSharedPreferences("BookingProfile", AppCompatActivity.MODE_PRIVATE)

        val cruiseType = shared?.getString("CruiseType", "")


       /*
        cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javad,Ali","$400","3 nights",2)
        cruiseViewModel.insertCruise(requireContext(),"Caribbean","Zohreh,Ali","$500","4",1)
        cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javid,Ali","$800","8",3)
       cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javad,Ali","$400","3",4)
       cruiseViewModel.insertCruise(requireContext(),"Caribbean","Shirin,Ali","$200","1",5)
        */

        cruiseViewModel.getCruisesByName(requireContext(),cruiseType.toString())!!.observe(viewLifecycleOwner
            , Observer { it ->

                var listView = view.findViewById(R.id.listViewCruisesSearch) as ListView
                //var txtname = listView.findViewById(R.id.columnName_listview) as TextView
                var itemList : ArrayList<String> = ArrayList()

                for (cruise in it)
                {
                    arrayList.add(cruise)
                }

                var adapter = ListViewAdapter(requireContext(),arrayList)

                listView.adapter = adapter
                listView.onItemClickListener=this
            })

        /*
        btnNext.setOnClickListener{

            Toast.makeText( requireContext(),"Please Select One Cruise Type", Toast.LENGTH_LONG).show()

        }*/

    }

    override fun onItemClick(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {


        val cruiseName = p1!!.findViewById<TextView>(R.id.CruiseName).text.toString()
        val CruiseCode = p1!!.findViewById<TextView>(R.id.CruiseCode).text.toString()
        val duration = p1!!.findViewById<TextView>(R.id.duration).text.toString()
        val price = p1!!.findViewById<TextView>(R.id.price).text.toString()
        val visitingPlaces = p1!!.findViewById<TextView>(R.id.visitingPlace).text.toString()
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString("cruiseName", cruiseName)
        editor?.putString("CruiseCode",CruiseCode)
        editor?.putString("duration",duration)
        editor?.putString("price",price)
        editor?.putString("visitingPlaces",visitingPlaces)
        editor?.commit()
        (activity as MainMenuActivity).goToPackageDetailFragment()


    }

}