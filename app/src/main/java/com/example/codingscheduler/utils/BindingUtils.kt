package com.example.codingscheduler.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.dataclass.CardItem


@BindingAdapter("bind_items","bind_model")
fun setBindiItems(view : RecyclerView, mList : MutableLiveData<ArrayList<CardItem>>, model: MainViewModel) {
    val adapter = view.adapter as? CardAdapter
        ?: CardAdapter(model).apply { view.adapter = this }
    adapter.mList = mList
    adapter.notifyDataSetChanged()
}