package com.example.codingscheduler

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingscheduler.RoomDB.CardRepo
import com.example.codingscheduler.dataclass.CardItem
import java.text.FieldPosition
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainViewModel:ViewModel() {
    val mList=MutableLiveData<ArrayList<CardItem>>()
    var isAddClicked=MutableLiveData<Boolean>()
    var isRecordClicked=MutableLiveData<Boolean>()
    var digTitle=MutableLiveData<String>()
    var digNumber=MutableLiveData<String>()
    var digType=MutableLiveData<String>()
    var digTags=MutableLiveData<ArrayList<String>>()
    var isTimeRunning=MutableLiveData<Boolean>()
    var isTimerShow=MutableLiveData<Boolean>()
    var selectedCard=MutableLiveData<CardItem?>()
    var time=MutableLiveData<Long>()
    var timeTask:Timer?=null
    var recordColor=MutableLiveData<String>()
    init {
        modelValuesInit()
    }
    fun addCard(item: CardItem) {
        CardRepo.insert(item)
//        mList.value!!.add(item)

    }
    fun toggleIsAddClicked(){
        isAddClicked.value=isAddClicked.value!!.not()
    }
    fun submitClicked(){
        if(!dataNullOrBlankCheck(digTitle))
        addCard(CardItem(0,digTitle.value!!, digNumber.value?:"","1",ArrayList(), ArrayList()))
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
        showPopUp()
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
    fun handleRecord(mode:Int){
        saveTime(mode)
        closePopUp()
    }
    fun showPopUp() = isRecordClicked.postValue(true)
    fun closePopUp() = isRecordClicked.postValue(false)
    fun saveTime(mode:Int){
        val tmpList=ArrayList<CardItem>()
        tmpList.addAll(mList.value!!)
        tmpList.remove(selectedCard.value)
        val cardTimeList=selectedCard.value!!.times!!
        if(cardTimeList.size==3) cardTimeList.clear()
        cardTimeList.add(Pair(time.value!!,mode))
        tmpList.add(selectedCard.value!!)
//        mList.postValue(tmpList)
        CardRepo.updateTimes(cardTimeList,selectedCard.value!!.id)
    }
    fun modelValuesInit(){
        mList.value= ArrayList()
        isAddClicked.value=false
        isTimeRunning.value=false
        isTimerShow.value=false
        isRecordClicked.value=false
        selectedCard.value=null
        time.value=0
    }
    fun makeTimeFormat(timeVal:Long):String{
        var tmpTime=timeVal
        val hour:Long=tmpTime/3600
        tmpTime-=hour*3600
        val min:Int=(tmpTime/60).toInt()
        tmpTime-=min*60
        val sec=tmpTime
        return "%d:%02d:%02d".format(hour,min,sec)
    }
}