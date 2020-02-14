package com.example.codingscheduler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.collections.ArrayList

public class MainViewModel:ViewModel() {
    val mList=MutableLiveData<ArrayList<CardItem>>()
    init {
        var list=ArrayList<CardItem>()
        list.add(CardItem("test1",1))
        list.add(CardItem("test2",2))
        mList.value=list

    }
    fun addCard(item:CardItem) = mList.value!!.add(item)
}