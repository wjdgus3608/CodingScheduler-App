package com.example.codingscheduler

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.codingscheduler.dataclass.CardItem
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainViewModel:ViewModel() {
    val mList=MutableLiveData<ArrayList<CardItem>>()
    var isAddClicked=MutableLiveData<Boolean>()
    var digTitle=MutableLiveData<String>()
    var digNumber=MutableLiveData<String>()
    var digType=MutableLiveData<String>()
    var digTags=MutableLiveData<ArrayList<String>>()
    var isTimeRunning=MutableLiveData<Boolean>()
    var time=MutableLiveData<Long>()
    var timeTask:Timer?=null
    init {
        mList.value= ArrayList()
        isAddClicked.value=false
        isTimeRunning.value=false
        time.value=0
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
    fun timerHandler(){
        if(isTimeRunning.value!!) timerPause()
        else timerStart()
    }
    fun timerStart(){
        if(isTimeRunning.value!!) timerStop()
        isTimeRunning.value=true
        timeTask=timer(period = 1000){
            Log.e("log","time is : "+time.value)
            time.postValue(time.value!!+1)
        }
    }
    fun timerPause() {
        timeTask?.cancel()
        timeTask=null
        isTimeRunning.value=false
    }
    fun timerStop() {
        timeTask?.cancel()
        timeTask=null
        isTimeRunning.value=false
        saveTime()
        time.value=0
    }
    fun saveTime(){

    }
}