package com.example.codingscheduler.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.R
import com.example.codingscheduler.databinding.FrgListBinding


class ListFragment:Fragment(){
    lateinit var model:MainViewModel
    lateinit var dialog:AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        val binding=DataBindingUtil.inflate<FrgListBinding>(inflater,R.layout.frg_list,container,false)
        model= MainViewModel()
        binding.setVariable(BR.vm,model)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var popupView=layoutInflater.inflate(R.layout.dig_add,null)
        val builder = AlertDialog.Builder(context!!)
        model.isAddClicked.observe(this, Observer {
            if(it) dialog=builder.setView(popupView).show()
        })


    }
}