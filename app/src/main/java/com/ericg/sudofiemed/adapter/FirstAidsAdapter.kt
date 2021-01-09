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
        firstAidItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.first_aid_item,
            parent,
            false
        )

        return FirstAidViewHolder(
            firstAidItemBinding!!.root
        )
    }

    override fun onBindViewHolder(holder: FirstAidViewHolder, position: Int) {
        holder.bind(firstAidsList[position])
    }

    override fun getItemCount(): Int = firstAidsList.size

    inner class FirstAidViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val item = view

        init {
            item.setOnClickListener(this)
        }

        fun bind(firstAid: FirstAid) {
            firstAidItemBinding?.firstAidName?.text = firstAid.title ?: "First Aid"
        }

        override fun onClick(itemView: View?) {
            itemClick.itemDetails(itemView, adapterPosition)
        }

    }

    interface ItemClick {
        fun itemDetails(view: View?, position: Int)
    }
}