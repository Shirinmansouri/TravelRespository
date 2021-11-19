package com.example.group6_mapd711_assignment4

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CruiseDao {
    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCruise(cruiseModel: CruiseModel)

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM cruise WHERE cruiseCode =:cruiseCode")
    fun getCruises(cruiseCode: Int?) : LiveData<CruiseModel>

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM cruise  ")
    fun getAllCruises() : LiveData<List<CruiseModel>>

}