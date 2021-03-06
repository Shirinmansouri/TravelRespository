package com.example.group6_mapd711_assignment4

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookingDao {
    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooking(bookingModel: BookingModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBooking(bookingModel: BookingModel)

    @Delete()
    fun DeleteBooking(bookingModel: BookingModel)
    //defining a query method using @Query Annotation
    @Query("SELECT * FROM booking WHERE bookingId =:bookingId")
    fun getBookings(bookingId: Int?) : LiveData<BookingModel>

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM booking WHERE customerId =:customerId")
    fun getBookingsByCustomer( customerId: Int?) : LiveData<List<BookingModel>>

    @Query("SELECT * FROM booking")
    fun getBookingsWithNothing() : LiveData<List<BookingModel>>
}