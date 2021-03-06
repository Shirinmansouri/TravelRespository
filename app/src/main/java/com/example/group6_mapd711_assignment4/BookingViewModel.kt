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
    var l : LiveData<List<BookingModel>>? = null

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
    fun deleteBooking(context: Context, numberOfAdults: String, numberOfKids: String,
                      numberOfSeniors: String,
                      amountPaid: String,
                      startDate : String,
                      customerId : Int,
                      cruiseCode : Int,
                      bookingId : Int) {
        BookingRepository.DeleteBooking(context, numberOfAdults, numberOfKids,numberOfSeniors,amountPaid,
            startDate,customerId,cruiseCode,bookingId)
    }

    fun getBookings(context: Context, bookingId: Int?) : LiveData<BookingModel>? {
        liveDataBook = BookingRepository.getBookings(context, bookingId)
        return liveDataBook
    }
    fun getBookingsByCustomer(context: Context, customerId: Int?) : LiveData<List<BookingModel>>? {
        l = BookingRepository.getBookingsByCustomer(context, customerId)
        return l
    }

    fun getBookingsWithNothing(context: Context) : LiveData<List<BookingModel>>? {
        l = BookingRepository.getBookingsWithNothing(context)
        return l
    }

}