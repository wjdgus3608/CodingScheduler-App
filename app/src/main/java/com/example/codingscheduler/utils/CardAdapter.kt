package com.example.codingscheduler.utils

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
            mList.value!![position].let { item -> with(holder){
                title.text=item.title
                number.text=item.number
            }}
        holder.itemView.delete_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                mList.value!!.removeAt(position)
                notifyItemRemoved(position)
            }
        })
        holder.itemView.timer_btn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                model.selectedCard.postValue(position)
                model.timerStart()
            }
        })
    }

    inner class MainViewHolder(binding: LayoutCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var title:TextView=itemView.card_title
        var number:TextView=itemView.card_num
//        var type:TextView=itemView.card
        var tags: LinearLayout =itemView.card_tag_container
    }

}