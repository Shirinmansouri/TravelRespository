package com.example.group6_mapd711_assignment4
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
class CustomerViewModel : ViewModel() {

    // calling repository tasks and
    // sending the results to the Activity
    var liveDataCustomer: LiveData<CustomerModel>? = null

    //
    fun updateCustomer(context: Context,userName: String, password: String,
                       firstname : String ,
                       lastname : String ,
                       address : String ,
                       city : String ,
                       postalCode : String ,
                       telephone : String ,
                       email : String,
                       customerId : Int?)
    {
        CustomerRepository.updateCustomer(context, userName, password,firstname,lastname,
            address,city,postalCode,telephone,email,
            customerId  )
    }
    fun insertCustomer(context: Context, userName: String, password: String,
                       firstname : String ,
                       lastname : String ,
                       address : String ,
                       city : String ,
                       postalCode : String ,
                       telephone : String ,
                       email : String) {
        CustomerRepository.insertCustomer(context, userName, password,firstname,lastname,
            address,city,postalCode,telephone,email)
    }

    fun getCustomer(context: Context, userName: String) : LiveData<CustomerModel>? {
        liveDataCustomer = CustomerRepository.getCustomer(context, userName)
        return liveDataCustomer
    }
}