package com.example.codingscheduler

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("bind_items")
fun setBindiItems(view : RecyclerView, mList : MutableLiveData<ArrayList<CardItem>>) {
    val adapter = view.adapter as? CardAdapter ?: CardAdapter().apply { view.adapter = this }
    adapter.mList = mList
    adapter.notifyDataSetChanged()
}