package com.example.group6_mapd711_assignment4

import android.content.Context
import androidx.room.*

@Database(entities =[CustomerModel::class,BookingModel ::class , CruiseModel :: class] , version = 4, exportSchema = false)
abstract class TravelDatabase : RoomDatabase() {
    //instantiating Customer DAO object
    abstract  fun cruiseDao() : CruiseDao
    abstract fun customerDao() : CustomerDao
    abstract  fun bookingDao() : BookingDao


    //companion object means an object declaration inside a class
    companion object {

        //Volatile object or property is immediately made visible to other threads.
        @Volatile
        private var INSTANCE: TravelDatabase? = null

        //create a database name "CustomerDB"
        fun getDataseClient(context: Context) : TravelDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, TravelDatabase::class.java, "TravelDB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}