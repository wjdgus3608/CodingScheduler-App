package com.example.codingscheduler.views

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.R
import com.example.codingscheduler.databinding.DigAddBinding
import com.example.codingscheduler.databinding.DigTimerBinding
import com.example.codingscheduler.databinding.FrgListBinding
import kotlinx.android.synthetic.main.dig_add.*
import kotlinx.android.synthetic.main.dig_timer.view.*
import kotlinx.android.synthetic.main.frg_list.*
import kotlinx.android.synthetic.main.layout_card.*
import kotlin.concurrent.timer


class ListFragment:Fragment(){
    val model by lazy {
        ViewModelProvider(activity!!.viewModelStore,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)}
    var dialog:AlertDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        val binding=DataBindingUtil.inflate<FrgListBinding>(inflater,R.layout.frg_list,container,false)
        binding.setVariable(BR.vm,model)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val popupView=DataBindingUtil.inflate<DigAddBinding>(layoutInflater,R.layout.dig_add,view as ViewGroup,false)
        popupView.setVariable(BR.vm,model)
        val timerView=DataBindingUtil.inflate<DigTimerBinding>(layoutInflater,R.layout.dig_timer,view,false)
        timerView.setVariable(BR.vm,model)
        val timerWindow=PopupWindow(timerView.root,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
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

        model.mList.observe(this, Observer { frg_recyclerView.adapter?.notifyDataSetChanged() })
        model.time.observe(this, Observer { timerView.root.time_view.text=model.makeTimeFormat(it) })
        model.isTimerShow.observe(this, Observer { if(it) timerWindow.showAsDropDown(view,0,-230) else timerWindow.dismiss() })
        model.selectedCard.observe(this, Observer { timerView.timerTitle.text=it?.title })
        model.isTimeRunning.observe(this, Observer { timerView.playBtn.setImageResource(if(it) R.drawable.ic_pause else R.drawable.ic_play) })
    }
}