package com.example.codingscheduler.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.R
import com.example.codingscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val model=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.setVariable(BR.vm, model)
        binding.setLifecycleOwner { this.lifecycle }
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            ListFragment(model)
        ).commit()
    }
}
