package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.sql.Date


//a class for managing multiple data sources
class BookingRepository {
    companion object {
        var travelDatabase: TravelDatabase? = null
        var bookingModel: LiveData<BookingModel>? = null
        var lstBookingModel: LiveData<List<BookingModel>>? = null

        //initialize database
        fun initializeDB(context: Context): TravelDatabase {
            return TravelDatabase.getDataseClient(context)
        }

        //Initialize insertBooking()
        fun insertBooking(
            context: Context, numberOfAdults: String, numberOfKids: String,
            numberOfSeniors: String,
            amountPaid: String,
            startDate : String,
            customerId : Int,
            cruiseCode : Int
        ) {
            travelDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val bookingDetails= BookingModel(numberOfAdults ,numberOfKids ,
                    numberOfSeniors ,
                    amountPaid,
                    startDate ,
                    customerId ,
                    cruiseCode
                )
                travelDatabase!!.bookingDao().insertBooking(bookingDetails)
            }

        }

        //Initialize getCruise()
        fun getBookings(context: Context, bookingId: Int?): LiveData<BookingModel>? {

            travelDatabase = initializeDB(context)
            bookingModel = travelDatabase!!.bookingDao().getBookings(bookingId)
            return bookingModel
        }
        //Initialize getBookingsByCustomer()
        fun getBookingsByCustomer(context: Context,customerId: Int?): LiveData<List<BookingModel>>? {

            travelDatabase = initializeDB(context)
            lstBookingModel = travelDatabase!!.bookingDao().getBookingsByCustomer(customerId)
            return lstBookingModel
        }

        fun getBookingsWithNothing(context: Context): LiveData<List<BookingModel>>? {

            travelDatabase = initializeDB(context)
            lstBookingModel = travelDatabase!!.bookingDao().getBookingsWithNothing()
            return lstBookingModel
        }

        fun UpdateBooking(context: Context ,  numberOfAdults: String, numberOfKids: String,
                           numberOfSeniors: String,
                           amountPaid: String,
                           startDate : String,
                           customerId : Int,
                           cruiseCode : Int,
                           bookingId : Int)
        {
        travelDatabase = initializeDB(context)

        CoroutineScope(Dispatchers.IO).launch {
            val bookingDetails= BookingModel(numberOfAdults ,numberOfKids ,
                numberOfSeniors ,
                amountPaid,
                startDate ,
                customerId ,
                cruiseCode,
                bookingId
            )
            travelDatabase!!.bookingDao().updateBooking(bookingDetails)
        }
    }
        fun DeleteBooking(context: Context ,  numberOfAdults: String, numberOfKids: String,
                          numberOfSeniors: String,
                          amountPaid: String,
                          startDate : String,
                          customerId : Int,
                          cruiseCode : Int,
                          bookingId : Int)
        {
            travelDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val bookingDetails= BookingModel(numberOfAdults ,numberOfKids ,
                    numberOfSeniors ,
                    amountPaid,
                    startDate ,
                    customerId ,
                    cruiseCode,
                    bookingId
                )
                travelDatabase!!.bookingDao().DeleteBooking(bookingDetails)
            }
        }
}
}