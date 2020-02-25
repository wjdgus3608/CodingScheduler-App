package com.example.codingscheduler.dataclass

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

data class CardItem(var title:String, var number:String, var type:String, var tags:ArrayList<String>?, var times:ArrayList<Long>?)