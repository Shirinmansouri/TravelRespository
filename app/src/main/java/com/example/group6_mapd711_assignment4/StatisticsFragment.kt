package com.example.group6_mapd711_assignment4;



import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ColorTemplate.rgb
import com.github.mikephil.charting.utils.Utils.init

import java.util.ArrayList;

public class StatisticsFragment : Fragment() {

    lateinit var bookingViewModel: BookingViewModel
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    lateinit var pieDataSet: PieDataSet
    lateinit var pieData: PieData

    var arrayList: ArrayList<BookingModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingViewModel = ViewModelProvider(this).get(BookingViewModel ::class.java)

        val shared = this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = shared?.edit()
        val customerId = shared?.getString("customerId", "")
        val view = inflater.inflate(R.layout.fragment_statistics, container, false);
        // Inflate the layout for this fragment
        var bahamasAmount = 0f
        var caribbeanAmount = 0f
        var cubaAmount = 0f
        var starAmount = 0f
        var samplerAmount = 0f

       // bookingViewModel.getBookingsByCustomer(requireContext(), customerId.toString().toInt())!!.observe(viewLifecycleOwner
        bookingViewModel.getBookingsWithNothing(requireContext())!!.observe(viewLifecycleOwner
            , Observer { it ->

                for (cruise in it)
                {
                    arrayList.add(cruise)

                }
                bahamasAmount = 0f
                caribbeanAmount = 0f
                cubaAmount = 0f
                starAmount = 0f
                samplerAmount = 0f

                for (i in arrayList){
                    if(i.cruiseCode == 2 || i.cruiseCode == 7 )
                    {
                        bahamasAmount += i.amountPaid.toFloat()
                    }
                    if(i.cruiseCode == 1 || i.cruiseCode == 8 )
                    {
                        caribbeanAmount += i.amountPaid.toFloat()
                    }
                    if(i.cruiseCode == 3 || i.cruiseCode == 9 )
                    {
                        cubaAmount += i.amountPaid.toFloat()
                    }
                    if(i.cruiseCode == 4 || i.cruiseCode == 10 )
                    {
                        starAmount += i.amountPaid.toFloat()
                    }
                    if(i.cruiseCode == 5 || i.cruiseCode == 6 )
                    {
                        samplerAmount += i.amountPaid.toFloat()
                    }
                }


                if (editor != null) {
                    editor.putString("BahamasAmount", bahamasAmount.toString())
                    editor.putString("CaribbeanAmount", caribbeanAmount.toString())
                    editor.putString("CubaAmount", cubaAmount.toString())
                    editor.putString("StarAmount", starAmount.toString())
                    editor.putString("SamplerAmount", samplerAmount.toString())
                    editor.commit()
                }

            })




        //val shared = this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        bahamasAmount = shared?.getString("BahamasAmount", "")?.toFloat()!!
        caribbeanAmount = shared?.getString("CaribbeanAmount", "")?.toFloat()!!
        cubaAmount = shared?.getString("CubaAmount", "")?.toFloat()!!
        starAmount = shared?.getString("StarAmount", "")?.toFloat()!!
        samplerAmount = shared?.getString("SamplerAmount", "")?.toFloat()!!

        val userName = shared?.getString("UserName", "")
        if(userName == "admin") {
            val pieChart = view.findViewById<PieChart>(R.id.barChart);
            val income = ArrayList<PieEntry>();


            if (bahamasAmount != 0f) {
                income += PieEntry(bahamasAmount, "Bahamas");
            }
            if (caribbeanAmount != 0f) {
                income += PieEntry(caribbeanAmount, "Caribbean");
            }
            if (cubaAmount != 0f) {
                income += PieEntry(cubaAmount, "Cuba");
            }
            if (starAmount != 0f) {
                income += PieEntry(starAmount, "Star");
            }
            if (samplerAmount != 0f) {
                income += PieEntry(samplerAmount, "Sampler");
            }
            var totalIncome = bahamasAmount + caribbeanAmount + cubaAmount + starAmount + samplerAmount

            pieDataSet = PieDataSet(income, "Income Chart");
            //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            pieDataSet.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db"),rgb("#808080"));
            pieDataSet.valueTextColor = Color.BLACK;
            pieDataSet.valueTextSize = 16f;

            pieData = PieData(pieDataSet);

            pieChart.data = pieData;
            pieChart.description.isEnabled = false;
            pieChart.centerText = "Total Income: U\$S $totalIncome"
            pieChart.animate();

        }



        //code for Bar Chart
//            val barChart = view.findViewById<BarChart>(R.id.barChart);
//            val income = ArrayList<BarEntry>();
//            income += BarEntry(1.0f,2222.5f );
//            income += BarEntry(2.0f,2676.5f );
//            income += BarEntry(3.0f,5000.5f );
//            income += BarEntry(4.0f,1367.5f );
//            income += BarEntry(5.0f,2222.5f );
//            income += BarEntry(6.0f,2676.5f );
//            income += BarEntry(7.0f,5000.5f );
//            income += BarEntry(8.0f,1367.5f );
//            income += BarEntry(9.0f,5000.5f );
//            income += BarEntry(10.0f,1367.5f );
//
//            barDataSet = BarDataSet(income, "Total income by Cruise Code");
//            //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//            barDataSet.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db"));
//            barDataSet.valueTextColor = Color.BLACK;
//            barDataSet.valueTextSize = 16f;
//
//            barData = BarData(barDataSet);
//
//            barChart.setFitBars(true);
//            barChart.data = barData;
//            barChart.description.text = "CRUISES SALES";
//            barChart.animateY(2000);

        return view;
    }

}

