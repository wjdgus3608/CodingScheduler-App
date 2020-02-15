package com.example.codingscheduler

import android.os.Debug
import android.util.Log
import android.widget.PopupWindow
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.collections.ArrayList

class MainViewModel:ViewModel() {
    val mList=MutableLiveData<ArrayList<CardItem>>()
    var isAddClicked=MutableLiveData<Boolean>()
    init {
        mList.value= ArrayList()
        isAddClicked.value=false
        addCard(CardItem("test1",1))
        addCard(CardItem("test2",2))

    }
    fun addCard(item:CardItem) = mList.value!!.add(item)
    fun toggleIsAddClicked(){
        isAddClicked.value=isAddClicked.value!!.not()
    }
}