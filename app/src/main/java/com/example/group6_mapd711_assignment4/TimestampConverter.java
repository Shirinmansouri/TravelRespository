package com.example.group6_mapd711_assignment4;

import android.annotation.SuppressLint;
import android.provider.SyncStateContract;

import androidx.room.TypeConverter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampConverter {
    @SuppressLint("SimpleDateFormat")
    static DateFormat df = new SimpleDateFormat();
    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return (Date) df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}