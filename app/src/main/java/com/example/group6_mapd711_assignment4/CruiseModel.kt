package com.example.group6_mapd711_assignment4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "cruise")
data class CruiseModel(

    @ColumnInfo(name = "cruiseName")
    var cruiseName: String,
    @ColumnInfo(name = "visitingPlaces")
    var visitingPlaces: String,
    @ColumnInfo(name = "price")
    var price: String,
    @ColumnInfo(name = "duration")
    var duration: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cruiseCode")
    var cruiseCode: Int? = null

)
