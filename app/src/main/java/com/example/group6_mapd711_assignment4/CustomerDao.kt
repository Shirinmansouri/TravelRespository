package com.example.group6_mapd711_assignment4

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {

    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customerModel: CustomerModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCustomer(customerModel: CustomerModel)

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM customer WHERE userName =:userName")
    fun getCustomers(userName: String?) : LiveData<CustomerModel>
}