package com.example.codingscheduler.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.R
import com.example.codingscheduler.RoomDB.CardRepo
import com.example.codingscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var model:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        CardRepo.initCardDao(application)
        model=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        CardRepo.getAllMemo().observe(this, Observer {
            model.mList.postValue(ArrayList(it)) })
        binding.setVariable(BR.vm, model)
        binding.setLifecycleOwner { this.lifecycle }
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            ListFragment.getInstance()
        ).commit()
    }
}
