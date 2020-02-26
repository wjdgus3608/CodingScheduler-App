package com.example.codingscheduler.RoomDB

import androidx.room.TypeConverter

class TimeConverter{
    @TypeConverter
    fun listToString2(value: MutableList<Long>): String {
        var str=""
        value.map { str=str+it.toString()+","}
        return str
    }

    @TypeConverter
    fun stringToList2(value: String): MutableList<Long>{
        if(!value.contains(",")) return ArrayList()
        val list= value.split(",").toMutableList()
        return list.subList(0,list.size-1).map { it.toLong() }.toMutableList()
    }
}