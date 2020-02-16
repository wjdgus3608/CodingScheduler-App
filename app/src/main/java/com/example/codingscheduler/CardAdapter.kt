package com.example.codingscheduler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.codingscheduler.dataclass.CardItem
import kotlinx.android.synthetic.main.layout_card.view.*

class CardAdapter :RecyclerView.Adapter<CardAdapter.MainViewHolder>(){
    var mList = MutableLiveData<ArrayList<CardItem>>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(parent)


    override fun getItemCount(): Int =mList.value!!.size



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            mList.value!![position].let { item -> with(holder){
                title.text=item.title
                number.text=item.number
            }}
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false)) {
        var title:TextView=itemView.card_title
        var number:TextView=itemView.card_num
//        var type:TextView=itemView.card
        var tags: LinearLayout =itemView.card_tag_container
    }

}