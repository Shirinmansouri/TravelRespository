package com.example.group6_mapd711_assignment4
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


//provides data to the UI and acts as a communication center
// between the Repository and the UI.
class BookingViewModel : ViewModel() {

    // calling repository tasks and
    // sending the results to the Activity
    var liveDataBook: LiveData<BookingModel>? = null
    var l : LiveData<BookingModel>? = null

    //
    fun insertBooking(context: Context, numberOfAdults: String, numberOfKids: String,
                      numberOfSeniors: String,
                      amountPaid: String,
                      startDate : String,
                      customerId : Int,
                      cruiseCode : Int) {
        BookingRepository.insertBooking(context, numberOfAdults, numberOfKids,numberOfSeniors,amountPaid,
            startDate,customerId,cruiseCode)
    }
    fun updateBooking(context: Context, numberOfAdults: String, numberOfKids: String,
                      numberOfSeniors: String,
                      amountPaid: String,
                      startDate : String,
                      customerId : Int,
                      cruiseCode : Int,
                     bookingId : Int) {
        BookingRepository.UpdateBooking(context, numberOfAdults, numberOfKids,numberOfSeniors,amountPaid,
            startDate,customerId,cruiseCode,bookingId)
    }

    fun getBookings(context: Context,cruiseCode: Int?, customerId: Int?) : LiveData<BookingModel>? {
        liveDataBook = BookingRepository.getBookings(context, cruiseCode, customerId)
        return liveDataBook
    }
    fun getBookingsByCustomer(context: Context, customerId: Int?) : LiveData<BookingModel>? {
        l = BookingRepository.getBookingsByCustomer(context, customerId)
        return l
    }
}