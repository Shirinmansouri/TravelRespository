package com.example.group6_mapd711_assignment4

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.sql.Date


@Entity(
    tableName = "booking",
    foreignKeys = [
        ForeignKey(
            entity = CruiseModel::class,
            parentColumns = ["cruiseCode"],
            childColumns = ["cruiseCode"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = CustomerModel::class,
            parentColumns = ["customerId"],
            childColumns = ["customerId"],
            onDelete = CASCADE
        ),
    ],
)

data class BookingModel(
    @ColumnInfo(name = "numberOfAdults")
    var numberOfAdults: String,
    @ColumnInfo(name = "numberOfKids")
    var numberOfKids: String,
    @ColumnInfo(name = "numberOfSeniors")
    var numberOfSeniors: String,
    @ColumnInfo(name = "amountPaid")
    var amountPaid: String,
    @ColumnInfo(name = "startDate")
    var startDate: String,
    @ColumnInfo(name = "customerId")
    var customerId: Int?,
    @ColumnInfo(name = "cruiseCode")
    var cruiseCode: Int?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookingId")
    var bookingId: Int? = null

)


