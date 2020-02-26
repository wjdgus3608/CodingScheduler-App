package com.example.codingscheduler.dataclass

import androidx.room.*

@Entity(tableName = "cards")
data class CardItem(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var title:String,
    var number:String,
    var type:String,
    var tags:MutableList<String>?,
    var times:MutableList<Long>?
)