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

class CruiseTypeFragment : Fragment(),AdapterView.OnItemClickListener {

    var isValid : Boolean = true

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById(R.id.btnNext1) as Button
        val chosenDate = view.findViewById<DatePicker>(R.id.datePicker1)
        var listView = view.findViewById(R.id.listViewCustomerSearch) as ListView
        var itemList : ArrayList<String> = ArrayList()
        itemList.add("Bahamas")
        itemList.add("Caribbean")
        itemList.add("Cuba")
        itemList.add("Sampler")
        itemList.add("Star")
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()

        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_single_choice, itemList)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener=this
        btnNext.setOnClickListener {

            val day = chosenDate.dayOfMonth
            var month = chosenDate.month
            //add 1 to month (0-11))
            month ++
            val year = chosenDate.year
            val strDate = "$year-$month-$day"
            //validate date
            val date = LocalDate.of(year,month,day)


         if (!isValid){

              Toast.makeText( requireContext(),"Please Select a Destination", Toast.LENGTH_LONG).show()
          }
            else  if(date < LocalDate.now()) {
                  Toast.makeText(context, "Invalid Date", Toast.LENGTH_LONG).show()
              }
              else
         {

              editor?.putString("StartDate", strDate)
              editor?.commit()
            (activity as MainMenuActivity).goToPackages()
          }
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
      var item: String = parent?.getItemAtPosition(position) as String
        val sharedPreferences : SharedPreferences? = this.activity?.getSharedPreferences("BookingProfile", 0)
        val editor : SharedPreferences.Editor? = sharedPreferences?.edit()
        if(item.isEmpty())
        isValid =false
        else
        {
        editor?.putString("CruiseType", item)
        editor?.commit()
        }
    }

}