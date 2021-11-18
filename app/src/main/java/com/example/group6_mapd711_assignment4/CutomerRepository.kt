package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

//a class for managing multiple data sources
class CustomerRepository {

    //defining database and live data object as companion objects
    companion object {
        var travelDatabase: TravelDatabase? = null
        var customerModel: LiveData<CustomerModel>? = null

        //initialize database
        fun initializeDB(context: Context) : TravelDatabase {
            return TravelDatabase.getDataseClient(context)
        }

        fun updateCustomer(context: Context, userName: String, password: String,
                           firstname : String ,
                           lastname : String ,
                           address : String ,
                           city : String ,
                           postalCode : String ,
                           telephone : String ,
                           email : String,
                           customerId : Int?
        )
            {
                travelDatabase = initializeDB(context)
                CoroutineScope(IO).launch {
                    val customerDetails = CustomerModel(userName, password,firstname,lastname,
                        address,city,postalCode,telephone,email,customerId)
                    travelDatabase!!.customerDao().updateCustomer(customerDetails)
                }
            }
        //Initialize insertCustomer()
        fun insertCustomer(context: Context, userName: String, password: String,
                           firstname : String ,
                           lastname : String ,
                           address : String ,
                           city : String ,
                           postalCode : String ,
                           telephone : String ,
                           email : String
                           ) {
            travelDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val customerDetails = CustomerModel(userName, password,firstname,lastname,
                    address,city,postalCode,telephone,email)
                travelDatabase!!.customerDao().insertCustomer(customerDetails)
            }

        }

        //Initialize getCustomers()
        fun getCustomer(context: Context, userName: String) : LiveData<CustomerModel>? {

            travelDatabase = initializeDB(context)
            customerModel = travelDatabase!!.customerDao().getCustomers(userName)
            return customerModel
        }

    }
}