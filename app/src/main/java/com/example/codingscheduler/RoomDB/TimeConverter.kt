package com.example.codingscheduler.RoomDB

import androidx.room.TypeConverter

class TimeConverter{
    @TypeConverter
    fun listToString2(value: MutableList<Pair<Long,Int>>): String {
        var str=""
        value.map { str="$str${it.first}/${it.second},"}
        return str
    }

    @TypeConverter
    fun stringToList2(value: String): MutableList<Pair<Long,Int>>{
        if(!value.contains(",")) return ArrayList()
        val list= value.split(",").toMutableList()
        return list.subList(0,list.size-1).map {
            val tmp=it.split("/")
            Pair(tmp[0].toLong(),tmp[1].toInt())
        }.toMutableList()
    }
}