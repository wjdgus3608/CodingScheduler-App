package com.example.codingscheduler.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.codingscheduler.dataclass.CardItem

@Database(entities = arrayOf(CardItem::class), version = 1)
@TypeConverters(TagConverter::class,TimeConverter::class)
abstract class CardDatabase : RoomDatabase() {
    abstract fun getCardDao(): CardDao

    companion object {

        private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase? {

            if(INSTANCE == null) {
                synchronized(CardDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CardDatabase::class.java,
                        "card.db")
                        .build()
                }
            }
            return INSTANCE
        }


    }
}