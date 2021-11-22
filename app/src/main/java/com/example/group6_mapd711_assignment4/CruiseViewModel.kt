package com.example.group6_mapd711_assignment4
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
class CruiseViewModel : ViewModel() {

    // calling repository tasks and
    // sending the results to the Activity
    var liveDataCruise: LiveData<CruiseModel>? = null
    var lstCruiseModel : LiveData<List<CruiseModel>>? = null
    //
    fun insertCruise(context: Context, cruiseName: String, visitingPlaces: String,
                       price : String ,
                       duration : String) {
        CruiseRepository.insertCruise(context, cruiseName, visitingPlaces, price, duration)
    }

    fun getCruises(context: Context, cruiseCode: Int?) : LiveData<CruiseModel>? {
        liveDataCruise = CruiseRepository.getCruises(context, cruiseCode)
        return liveDataCruise
    }
    fun getAllCruises(context: Context) : LiveData<List<CruiseModel>>? {
        lstCruiseModel = CruiseRepository.getAllCruises(context)
        return lstCruiseModel
    }

    fun getCruisesByName(context: Context, cruiseName: String?) : LiveData<List<CruiseModel>>? {
        lstCruiseModel = CruiseRepository.getCruisesByName(context, cruiseName)
        return lstCruiseModel
    }
}