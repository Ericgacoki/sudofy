package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.adapter.FirstAidsAdapter
import com.ericg.sudofiemed.databinding.FragmentFirstAidsBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.model.FirstAid
import java.security.cert.Extension

class FirstAids : Fragment(), FirstAidsAdapter.ItemClick {
    private var firstAidsBinding: FragmentFirstAidsBinding? = null
    lateinit var adapter: FirstAidsAdapter
    private var listOfFirstAids: ArrayList<FirstAid> = arrayListOf()
    private var searchedFirstAids: ArrayList<FirstAid> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstAidsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first_aids, container, false)
        listOfFirstAids =
            arrayListOf(
                FirstAid("Headache", 0),
                FirstAid("Cut", 1),
                FirstAid("Fire", 2),
                FirstAid("Nose bleed", 3),
                FirstAid("Food poisoning", 4),
                FirstAid("Headache", 0),
                FirstAid("Cut", 1),
                FirstAid("Fire", 2),
                FirstAid("Nose bleed", 3),
                FirstAid("Food poisoning", 4)
            )
        adapter = FirstAidsAdapter(listOfFirstAids, this@FirstAids)

        searchFilter()

        return firstAidsBinding?.root?.apply {
            firstAidsBinding!!.firstAidsRecyclerview.adapter = adapter
        }
    }

    private fun searchFilter(){
        firstAidsBinding?.searchView
    }

    override fun itemDetails(view: View?, position: Int) {
        toast("Clicked item ${position + 1} - ${adapter.firstAidsList[position].title}", Extensions.ToastDuration.LONG)
    }
}