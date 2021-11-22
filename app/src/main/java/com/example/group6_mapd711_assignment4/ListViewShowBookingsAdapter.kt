package com.example.group6_mapd711_assignment4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewShowBookingsAdapter (private val context: Context, private val arrayList: java.util.ArrayList<BookingModel>) :
    BaseAdapter() {

        private lateinit var bookingId: TextView
        private lateinit var indexId: TextView
        private lateinit var amountPaid: TextView
        private lateinit var startDate: TextView

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
            convertView = LayoutInflater.from(context).inflate(R.layout.cutome_booking_list_view, parent, false)
            bookingId = convertView.findViewById(R.id.bookingId)
            indexId = convertView.findViewById(R.id.indexNumber)
            amountPaid = convertView.findViewById(R.id.amountPaid)
            startDate = convertView.findViewById(R.id.startDate)



            bookingId.setText(arrayList.get(position).bookingId.toString())
            indexId.setText((position+1).toString())
            amountPaid.setText(arrayList.get(position).amountPaid)
            startDate.setText(arrayList.get(position).startDate)


            return convertView
        }
    }
