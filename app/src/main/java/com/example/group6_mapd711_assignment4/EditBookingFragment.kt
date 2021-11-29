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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.math.roundToInt

class EditBookingFragment : Fragment() {

    lateinit var bookingViewModel: BookingViewModel
    lateinit var cruiseViewModel: CruiseViewModel
    lateinit var bookingModel:  BookingModel
    lateinit var oldDate : LocalDate
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
        return inflater.inflate(R.layout.fragment_edit_booking, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingViewModel = ViewModelProvider(this).get(BookingViewModel ::class.java)
        cruiseViewModel = ViewModelProvider(this).get(CruiseViewModel ::class.java)



      //  var txtNewDate = view.findViewById(R.id.txtNewDate) as TextView
        /*var txtAmountPaid = view.findViewById(R.id.txtAmountPaid) as EditText
        var txtCustomerId = view.findViewById(R.id.txtCustomerId) as EditText
        var txtCruiseCode = view.findViewById(R.id.txtCruiseCode) as EditText
        var txtBookingCode = view.findViewById(R.id.txtBookingCode) as EditText*/
        var txtAmountPaid : String = ""
        var txtCustomerId : String = ""
        var txtCruiseCode : String = ""
        var txtBookingCode : String = ""
        var IsValidForCancel : Boolean = false


        val shared = this.activity?.getSharedPreferences("BookingProfile", AppCompatActivity.MODE_PRIVATE)
        val bookingId = shared?.getString("bookingID", "")
        val btnEditBooking = view.findViewById(R.id.btnEditBooking) as Button
        val btnCancel= view.findViewById<Button>(R.id.cancelBooking)
        bookingViewModel.getBookings(requireContext(), bookingId!!.toInt())!!.observe(viewLifecycleOwner
            , Observer {

                ( view.findViewById(R.id.spinnerNewNumberOfAdults) as Spinner).setSelection(it.numberOfAdults.toInt())
                ( view.findViewById(R.id.spinnerNewNumberOfKids) as Spinner).setSelection(it.numberOfKids.toInt())
                ( view.findViewById(R.id.spinnerNewNumberOfSeniors) as Spinner).setSelection(it.numberOfSeniors.toInt())

                val parsedDate: List<String> = it.startDate.split("-")
                var day = parsedDate[2].toInt()
                var month = parsedDate[1].toInt()
                var year = parsedDate[0].toInt()

                ( view.findViewById(R.id.datePicker2) as DatePicker).updateDate(year, month-1, day)
                txtAmountPaid =   it.amountPaid
                txtCustomerId  =   it.customerId.toString()
                txtCruiseCode  =   it.cruiseCode.toString()
                txtBookingCode =   it.bookingId.toString()
                val date = LocalDate.of(year,month,day)
               // val checkDate = LocalDate.of(year,month,day-14)
                var period = Period.between( LocalDate.now(),date )
                if (period.days >=14 || period.months>=1 || period.years>=1) {
                    IsValidForCancel = true
                }

            })

            btnCancel.setOnClickListener {
                /*bookingViewModel.deleteBooking(
                    requireContext(),
                    bookingModel.numberOfAdults,
                    bookingModel.numberOfKids,
                    bookingModel.numberOfSeniors,
                    bookingModel.amountPaid,
                    bookingModel.startDate,
                    bookingModel.customerId!!,
                    bookingModel.cruiseCode!!,
                    bookingModel.bookingId!!
                )*/
                if (IsValidForCancel)
                {
                var NumberOfAdults = view.findViewById(R.id.spinnerNewNumberOfAdults) as Spinner
                var NewNumberOfKids = view.findViewById(R.id.spinnerNewNumberOfKids) as Spinner
                var NewNumberOfSeniors = view.findViewById(R.id.spinnerNewNumberOfSeniors) as Spinner

                var chosenDate = view.findViewById(R.id.datePicker2) as DatePicker
                val day = chosenDate.dayOfMonth
                var month = chosenDate.month
                month ++
                val year = chosenDate.year
                val strDate = "$year-$month-$day"

                bookingViewModel.deleteBooking(requireContext(),  NumberOfAdults.selectedItem.toString(),NewNumberOfKids.selectedItem.toString(),NewNumberOfSeniors.selectedItem.toString(),
                    txtAmountPaid.trim(),strDate,txtCustomerId.toInt(),txtCruiseCode.toInt(),txtBookingCode.toInt())
                    Toast.makeText( context,"Booking Successfully Canceled", Toast.LENGTH_LONG).show()
                    (activity as MainMenuActivity).goToHome()
            }

        else
        {
            //var toShowToast : String = checkDate.toString()
            Toast.makeText( context,"It is too late to Cancel!", Toast.LENGTH_LONG).show()
        }}
        btnEditBooking.setOnClickListener{

            var NumberOfAdults = view.findViewById(R.id.spinnerNewNumberOfAdults) as Spinner
            var NewNumberOfKids = view.findViewById(R.id.spinnerNewNumberOfKids) as Spinner
            var NewNumberOfSeniors = view.findViewById(R.id.spinnerNewNumberOfSeniors) as Spinner

                /*
                var price : String = ""
                cruiseViewModel.getCruises(requireContext(), txtCruiseCode.text.toString().toInt())!!.observe(viewLifecycleOwner,
                {
                    price = it.price
                })

                var finalPrice1 = (price?.let { (txtNumberOfAdults.text.toString())?.toFloat()?.times(it.toFloat()) })
                var finalPrice2 = price?.toFloat()?.let {
                    (txtNewNumberOfKids.text.toString())?.toFloat()
                        ?.times(it.div(2))
                }
                var finalPrice = finalPrice2?.let { finalPrice1?.plus(it) }
                var finalPriceWithTax = (finalPrice?.times(1.13))?.roundToInt().toString()
                */
            var chosenDate = view.findViewById(R.id.datePicker2) as DatePicker
            val day = chosenDate.dayOfMonth
            var month = chosenDate.month
            month ++
            val year = chosenDate.year
            val strDate = "$year-$month-$day"
                    bookingViewModel.updateBooking(requireContext(),  NumberOfAdults.selectedItem.toString(),NewNumberOfKids.selectedItem.toString(),NewNumberOfSeniors.selectedItem.toString(),
                        txtAmountPaid.trim(),strDate,txtCustomerId.toInt(),txtCruiseCode.toInt(),txtBookingCode.toInt())

                    Toast.makeText( context,"Information Successfully Updated", Toast.LENGTH_LONG).show()


        }

    }

}