package com.example.codingscheduler.RoomDB

import androidx.room.TypeConverter

class TagConverter {

    @TypeConverter
    fun listToString(value: MutableList<String>): String {
        var str=""
        value!!.map { str=str+it+","}
        return str
    }

    @TypeConverter
    fun stringToList(value: String): MutableList<String> {
        val list= value.split(",").toMutableList()
        return list.subList(0,list.size-1)
    }
}