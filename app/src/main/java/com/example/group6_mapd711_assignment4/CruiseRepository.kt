package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CruiseRepository {
    companion object {
        var travelDatabase: TravelDatabase? = null
        var cruiseModel: LiveData<CruiseModel>? = null
       var lstCruiseModel :LiveData<List<CruiseModel>>? = null
        //initialize database
        fun initializeDB(context: Context): TravelDatabase {
            return TravelDatabase.getDataseClient(context)
        }


        //Initialize insertCruise()
        fun insertCruise(
            context: Context, cruiseName: String, visitingPlaces: String,
            price: String,
            duration: String
        ) {
            travelDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val cruiseDetails= CruiseModel( cruiseName,visitingPlaces,price,duration

                )
                travelDatabase!!.cruiseDao().insertCruise(cruiseDetails)
            }

        }

        //Initialize getCruise()
        fun getCruises(context: Context, cruiseCode: Int?): LiveData<CruiseModel>? {

            travelDatabase = initializeDB(context)
            cruiseModel = travelDatabase!!.cruiseDao().getCruises(cruiseCode)
            return cruiseModel
        }
        //Initialize getAllCruises()
        fun getAllCruises(context: Context): LiveData<List<CruiseModel>>? {

            travelDatabase = initializeDB(context)
            lstCruiseModel = travelDatabase!!.cruiseDao().getAllCruises()
            return lstCruiseModel
        }
        //Initialize getCruisesByName()
        fun getCruisesByName(context: Context, cruiseName: String?): LiveData<List<CruiseModel>>? {

            travelDatabase = initializeDB(context)
            lstCruiseModel = travelDatabase!!.cruiseDao().getCruisesByName(cruiseName)
            return lstCruiseModel
        }
    }
}