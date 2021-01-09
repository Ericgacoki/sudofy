package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.adapter.FirstAidsAdapter
import com.ericg.sudofiemed.databinding.FirstAidItemBinding
import com.ericg.sudofiemed.databinding.FragmentFirstAidsBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.model.FirstAid
import java.util.*

class FirstAids : Fragment(), SearchView.OnQueryTextListener, FirstAidsAdapter.ItemClick {
    private var firstAidsBinding: FragmentFirstAidsBinding? = null
    private lateinit var adapter: FirstAidsAdapter
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
                FirstAid("Headache", 0, ""),
                FirstAid("Cut", 1, ""),
                FirstAid("Fire", 2, ""),
                FirstAid("Nose bleed", 3, ""),
                FirstAid("Food poisoning", 4, ""),
                FirstAid("Headache", 0, ""),
                FirstAid("Fracture", 1, ""),
                FirstAid("Fire", 2, ""),
                FirstAid("Nose bleed", 3, ""),
                FirstAid("Food poisoning", 4, "")
            )
        adapter = FirstAidsAdapter(listOfFirstAids, this@FirstAids)

        searchFilter()
        firstAidsBinding!!.swipeToRefresh.setOnRefreshListener {
            onSwipe()
        }

        return firstAidsBinding?.root?.apply {

            firstAidsBinding!!.firstAidsRecyclerview.adapter = adapter
        }
    }

    private fun searchFilter() {
        firstAidsBinding?.searchView!!.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@FirstAids)
        }
    }

    override fun clickedItemDetails(view: View?, position: Int) {
        toast(
            "Clicked item ${position + 1} - ${adapter.firstAidsList[position].title}",
            Extensions.ToastDuration.LONG
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!!.isNotEmpty()) {
            searchedFirstAids = arrayListOf()

            listOfFirstAids.forEach { firstAid ->
                if (firstAid.title.trim().contains(query, true)) {
                    searchedFirstAids.add(firstAid)
                }
                adapter.firstAidsList = searchedFirstAids
            }
        } else {
            adapter.firstAidsList = listOfFirstAids
        }
        adapter.notifyDataSetChanged()
        updateUI()

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!!.isNotEmpty()) {
            searchedFirstAids = arrayListOf()

            listOfFirstAids.forEach { firstAid ->
                if (firstAid.title.trim().contains(newText, true)
                ) {
                    searchedFirstAids.add(firstAid)
                }
                adapter.firstAidsList = searchedFirstAids
            }
        } else {
            adapter.firstAidsList = listOfFirstAids
        }
        adapter.notifyDataSetChanged()
        updateUI()

        return true
    }

    private fun updateUI() {
        if (adapter.firstAidsList.isEmpty()) {
            // Todo set no data found image visible
        }
    }

    private fun onSwipe() {
        adapter.firstAidsList = listOfFirstAids
        adapter.notifyDataSetChanged()
        updateUI()

        firstAidsBinding!!.swipeToRefresh.isRefreshing = false
    }
}