package com.example.codingscheduler

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codingscheduler.dataclass.CardItem
import kotlin.collections.ArrayList

class MainViewModel:ViewModel() {
    val mList=MutableLiveData<ArrayList<CardItem>>()
    var isAddClicked=MutableLiveData<Boolean>()
    var digTitle=MutableLiveData<String>()
    var digNumber=MutableLiveData<String>()
    var digType=MutableLiveData<String>()
    var digTags=MutableLiveData<ArrayList<String>>()
    init {
        mList.value= ArrayList()
        isAddClicked.value=false
        addCard(CardItem("test1", "1","1", ArrayList()))
        addCard(CardItem("test2", "2","1", ArrayList()))

    }
    fun addCard(item: CardItem) = mList.value!!.add(item)
    fun toggleIsAddClicked(){
        isAddClicked.value=isAddClicked.value!!.not()
    }
    fun submitClicked(){
        if(!dataNullOrBlankCheck(digTitle))
        addCard(CardItem(digTitle.value!!, digNumber.value?:"","1", ArrayList()))
        clearDigData()
        toggleIsAddClicked()
    }
    fun clearDigData(){
        digTitle.value=""
        digNumber.value=""
    }
    fun dataNullOrBlankCheck(data:MutableLiveData<String>)=data.value.isNullOrBlank()
}