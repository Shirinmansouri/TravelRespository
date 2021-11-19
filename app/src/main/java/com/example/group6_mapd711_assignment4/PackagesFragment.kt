package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
        val btnNext = view.findViewById(R.id.btnNext2) as Button

       cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javad,Ali","$400","3 nights")
        //cruiseViewModel.insertCruise(requireContext(),"Caribbean","Zohreh,Ali","$500","4")
        //cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javid,Ali","$800","8")
       // cruiseViewModel.insertCruise(requireContext(),"Bahamas","Javad,Ali","$400","3")
       // cruiseViewModel.insertCruise(requireContext(),"Caribbean","Shirin,Ali","$200","1")

        cruiseViewModel.getAllCruises(requireContext())!!.observe(viewLifecycleOwner
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
    }

    override fun onItemClick(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var item: String = parent?.getItemAtPosition(position) as String
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString("CruiseCode", item)
        editor?.commit()
    }

}