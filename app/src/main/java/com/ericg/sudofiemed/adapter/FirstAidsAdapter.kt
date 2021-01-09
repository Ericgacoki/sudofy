package com.ericg.sudofiemed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FirstAidItemBinding
import com.ericg.sudofiemed.model.FirstAid

class FirstAidsAdapter(

    var firstAidsList: ArrayList<FirstAid>,
    private val itemClick: ItemClick

) : RecyclerView.Adapter<FirstAidsAdapter.FirstAidViewHolder>() {

    private var firstAidItemBinding: FirstAidItemBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstAidViewHolder {

        firstAidItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.first_aid_item, parent, false)
        return FirstAidViewHolder(
            firstAidItemBinding!!
        )
    }

    override fun onBindViewHolder(holder: FirstAidViewHolder, position: Int) {
        holder.bind(firstAidsList[position])
    }

    override fun getItemCount(): Int = firstAidsList.size

    inner class FirstAidViewHolder(view: FirstAidItemBinding) : RecyclerView.ViewHolder(view.root),
        View.OnClickListener {
        private val item = view.root

        init {
            item.setOnClickListener(this)
        }

        fun bind(firstAid: FirstAid) {
            firstAidItemBinding!!.firstAidName.text = firstAid.title
        }

        override fun onClick(itemView: View?) {
            itemClick.clickedItemDetails(itemView, adapterPosition)
        }

    }

    interface ItemClick {
        fun clickedItemDetails(view: View?, position: Int)
    }
}