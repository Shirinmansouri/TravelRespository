package com.example.group6_mapd711_assignment4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customer")
data class CustomerModel(
    @ColumnInfo(name = "userName")
    var userName: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "firstname")
    var firstName: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "lastname")
    var lastName: String,
    @ColumnInfo(name = "address")
    var address: String,
    @ColumnInfo(name = "city")
    var city: String,
    @ColumnInfo(name = "postalCode")
    var postalCode: String,
    @ColumnInfo(name = "telephone")
    var telephone: String,

    @PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "customerId")
var customerId: Int? = null

)
