package com.ericg.sudofiemed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ericg.sudofiemed.databinding.FirstAidItemBinding
import com.ericg.sudofiemed.model.FirstAidData

class FirstAidsAdapter(
    var firstAidsListData: ArrayList<FirstAidData>,
    private val itemClick: ItemClick

) : RecyclerView.Adapter<FirstAidsAdapter.FirstAidViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstAidViewHolder {

        return FirstAidViewHolder(
            FirstAidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FirstAidViewHolder, position: Int) {
        holder.bind(firstAidsListData[position])
    }

    override fun getItemCount(): Int = firstAidsListData.size

    inner class FirstAidViewHolder(val view: FirstAidItemBinding) :
        RecyclerView.ViewHolder(view.root),
        View.OnClickListener {
        private val item: View = view.root

        init {
            item.setOnClickListener(this)
        }

        fun bind(firstAidData: FirstAidData) {
            view.apply {
                firstAidImage.setImageDrawable(firstAidData.image)
                firstAidImage.clipToOutline = true
                firstAidName.text = firstAidData.title
            }
        }

        override fun onClick(itemView: View?) {
            itemClick.clickedItemDetails(itemView, adapterPosition)
        }
    }

    interface ItemClick {
        fun clickedItemDetails(view: View?, position: Int)
    }
}