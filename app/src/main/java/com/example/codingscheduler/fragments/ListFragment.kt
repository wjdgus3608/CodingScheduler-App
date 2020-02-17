package com.example.codingscheduler.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.codingscheduler.databinding.DigAddBinding
import com.example.codingscheduler.databinding.FrgListBinding
import kotlinx.android.synthetic.main.dig_add.*
import kotlinx.android.synthetic.main.frg_list.*
import kotlinx.android.synthetic.main.layout_card.*


class ListFragment:Fragment(){
    lateinit var model:MainViewModel
    var dialog:AlertDialog?=null

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
        var popupView=DataBindingUtil.inflate<DigAddBinding>(layoutInflater,R.layout.dig_add,view as ViewGroup,false)
        popupView.setVariable(BR.vm,model)
        val builder = AlertDialog.Builder(context!!)
        dialog=builder.setView(popupView.root).create()
        model.isAddClicked.observe(this, Observer {
            if(it) {
                dialog?.show()
            }
            else dialog?.dismiss()
            popupView.digTitle.text?.clear()
            popupView.digNumber.text?.clear()
        })
        model.time.observe(this, Observer { time_view.text=it.toString() })
    }
}