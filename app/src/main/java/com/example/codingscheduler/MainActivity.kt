package com.example.codingscheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.codingscheduler.Fragment.ListFragment
import com.example.codingscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.setVariable(BR.vm, MainViewModel())
        binding.setLifecycleOwner { this.lifecycle }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            ListFragment()
        ).commit()
    }
}
