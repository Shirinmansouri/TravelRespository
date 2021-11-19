package com.example.group6_mapd711_assignment4

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CruiseTypeFragment : Fragment(),AdapterView.OnItemClickListener {
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
        return inflater.inflate(R.layout.fragment_cruise_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById(R.id.btnNext1) as Button
        var listView = view.findViewById(R.id.listViewCustomerSearch) as ListView
        var itemList : ArrayList<String> = ArrayList()
        itemList.add("Bahamas Cruise")
        itemList.add("Caribbean Cruise")
        itemList.add("Cuba Cruise")
        itemList.add("Sampler Cruise")
        itemList.add("Star Cruise")


        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_single_choice, itemList)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener=this
        btnNext.setOnClickListener {
          if (listView.selectedItemId == null)
          {
              Toast.makeText( requireContext(),"Please Select One Cruise Type", Toast.LENGTH_LONG).show()
          }
            else
          {
            (activity as MainMenuActivity).goToPackages()
          }
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
      var item: String = parent?.getItemAtPosition(position) as String
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString("CruiseType", item)
        editor?.commit()
    }

}