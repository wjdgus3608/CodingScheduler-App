package com.example.codingscheduler.RoomDB

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.TypeConverters
import com.example.codingscheduler.dataclass.CardItem

object CardRepo{
    lateinit var cardDao: CardDao
    fun initCardDao(application: Application){
        val db = CardDatabase.getInstance(application)!!
        cardDao=db.getCardDao()
    }
    private val cards: LiveData<MutableList<CardItem>> by lazy {
        cardDao.getAllCard()
    }

    fun getAllMemo(): LiveData<MutableList<CardItem>> {
        return cards
    }

    fun insert(card: CardItem){
        Thread(Runnable { cardDao.insert(card) }).start()
    }
    fun delete(id: Long){
        Thread(Runnable { cardDao.deleteById(id) }).start()
    }
    fun updateTimes(list:MutableList<Long>, id:Long){
        var str=""
        list.map { str+="$it," }
        Thread(Runnable { cardDao.updateTimes(str,id) }).start()
    }
}