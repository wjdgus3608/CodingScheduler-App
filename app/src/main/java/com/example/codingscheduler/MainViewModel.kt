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
    var isTimerShow=MutableLiveData<Boolean>()
    var selectedCard=MutableLiveData<CardItem?>()
    var time=MutableLiveData<Long>()
    var timeTask:Timer?=null
    init {
        modelValuesInit()
        addCard(CardItem("test1", "1","1", ArrayList(), ArrayList()))
        addCard(CardItem("test2", "2","1", ArrayList(), ArrayList()))
    }
    fun addCard(item: CardItem) = mList.value!!.add(item)
    fun toggleIsAddClicked(){
        isAddClicked.value=isAddClicked.value!!.not()
    }
    fun submitClicked(){
        if(!dataNullOrBlankCheck(digTitle))
        addCard(CardItem(digTitle.value!!, digNumber.value?:"","1", ArrayList(), ArrayList()))
        toggleIsAddClicked()
    }
    fun dataNullOrBlankCheck(data:MutableLiveData<String>)=data.value.isNullOrBlank()
    fun showTimer() = isTimerShow.postValue(true)
    fun hideTimer(){
        isTimerShow.postValue(false)
        timerInit()
    }
    fun timerStart(){
        if(isTimeRunning.value!!) timerStop()
        isTimeRunning.value=true
        timeTask=timer(period = 1000){
            Log.e("log","time is : "+time.value)
            time.postValue(time.value!!+1)
        }
    }
    fun timerHandler(){
        if(isTimeRunning.value!!) timerPause()
        else timerStart()
    }
    fun timerInit(){
        timerStop()
    }
    fun timerRecord(){
        saveTime()
        timerPause()
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
        time.postValue(0)
    }
    fun saveTime(){
        var tmpList=ArrayList<CardItem>()
        tmpList.addAll(mList.value!!)
        tmpList.remove(selectedCard.value)
        if(selectedCard.value!!.times!!.size==3) selectedCard.value!!.times!!.clear()
        selectedCard.value!!.times!!.add(time.value!!)
        tmpList.add(selectedCard.value!!)
        mList.postValue(tmpList)
    }
    fun modelValuesInit(){
        mList.value= ArrayList()
        isAddClicked.value=false
        isTimeRunning.value=false
        isTimerShow.value=false
        selectedCard.value=null
        time.value=0
    }
    fun makeTimeFormat(timeVal:Long):String{
        var tmpTime=timeVal
        var hour:Long=tmpTime!!/3600
        tmpTime-=hour*3600
        var min:Int=(tmpTime/60).toInt()
        tmpTime-=min*60
        var sec=tmpTime
        return "$hour:$min:$tmpTime"
    }
}