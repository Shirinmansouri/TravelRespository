package com.example.group6_mapd711_assignment4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val context: Context, private val arrayList: java.util.ArrayList<CruiseModel>) :
    BaseAdapter() {
    private lateinit var CruiseCode: TextView
    private lateinit var CruiseName: TextView
    private lateinit var price: TextView
    private lateinit var duration: TextView
    private lateinit var visitingPlace: TextView
    private lateinit var choose : TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false)
        CruiseCode = convertView.findViewById(R.id.CruiseCode)
        CruiseName = convertView.findViewById(R.id.CruiseName)
        price = convertView.findViewById(R.id.price)
        duration = convertView.findViewById(R.id.duration)
        visitingPlace = convertView.findViewById(R.id.visitingPlace)
        choose = convertView.findViewById(R.id.choosePackage)

        CruiseCode.setText(arrayList.get(position).cruiseCode.toString())
        CruiseName.setText(arrayList.get(position).cruiseName)
        price.setText(arrayList.get(position).price)
        duration.setText(arrayList.get(position).duration)
        visitingPlace.setText(arrayList.get(position).visitingPlaces)
        choose.setText("choose")




        /*serialNum.text = " " + arrayList[position].num
        name.text = arrayList[position].name
        contactNum.text = arrayList[position].mobileNumber*/
        return convertView
    }
}