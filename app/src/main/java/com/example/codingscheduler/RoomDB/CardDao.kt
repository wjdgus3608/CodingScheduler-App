package com.example.codingscheduler.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.codingscheduler.dataclass.CardItem

@Dao
interface CardDao{
    @Query("SELECT * FROM cards")
    fun getAllCard(): LiveData<MutableList<CardItem>>

    @Query("DELETE FROM cards WHERE id=:id")
    fun deleteById(id: Long)

    //해당 데이터를 추가합니다.

    @Insert
    fun insert(vararg card: CardItem)

    //헤당 데이터를 업데이트 합니다.
    @Query("UPDATE cards SET times=:value WHERE id = :id")
    fun updateTimes(value: String, id: Long)


}