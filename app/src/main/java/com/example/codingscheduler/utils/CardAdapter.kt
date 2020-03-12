package com.example.codingscheduler.utils

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.codingscheduler.MainViewModel
import com.example.codingscheduler.R
import com.example.codingscheduler.RoomDB.CardRepo
import com.example.codingscheduler.databinding.LayoutCardBinding
import com.example.codingscheduler.dataclass.CardItem
import kotlinx.android.synthetic.main.layout_card.view.*

class CardAdapter(parentModel: MainViewModel) :RecyclerView.Adapter<CardAdapter.MainViewHolder>(){
    var mList = MutableLiveData<ArrayList<CardItem>>()
    var model: MainViewModel

    init {
        model=parentModel
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var binding:LayoutCardBinding=DataBindingUtil.inflate(inflater,
            R.layout.layout_card,parent,false)
        binding.setVariable(BR.vm,model)
       return MainViewHolder(binding)
    }


    override fun getItemCount(): Int =mList.value!!.size



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.itemView.setOnLongClickListener(object :View.OnLongClickListener{
                override fun onLongClick(v: View?): Boolean {
                    Log.e("log","Long Click $position")

                    return false
                }
            })
            val RED=Color.parseColor("#ff0000")
            val GREEN=Color.parseColor("#00ff00")
            mList.value!![position].let { item -> with(holder){
                title.text=item.title
                number.text=item.number
                val size=item.times!!.size
                if(size>=3) {
                    time3.text = model.makeTimeFormat(item.times!![2].first)
                    time3.setTextColor(if(item.times!![2].second==0) GREEN else RED)
                }
                if(size>=2){
                    time2.text=model.makeTimeFormat(item.times!![1].first)
                    time2.setTextColor(if(item.times!![1].second==0) GREEN else RED)
                }
                if(size>=1){
                    time1.text=model.makeTimeFormat(item.times!![0].first)
                    time1.setTextColor(if(item.times!![0].second==0) GREEN else RED)
                }
            }}
        holder.itemView.delete_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                CardRepo.delete(mList.value!![position].id)
                mList.value!!.removeAt(position)
                notifyItemRemoved(position)
            }
        })
        holder.itemView.timer_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                model.selectedCard.postValue(mList.value!![position])
                if(model.isTimerShow.value!!) model.hideTimer()
                model.showTimer()
            }
        })
    }

    inner class MainViewHolder(binding: LayoutCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var title:TextView=itemView.card_title
        var number:TextView=itemView.card_num
        var time1:TextView=itemView.card_record1
        var time2:TextView=itemView.card_record2
        var time3:TextView=itemView.card_record3
//        var type:TextView=itemView.card
        var tags: LinearLayout =itemView.card_tag_container
    }

}