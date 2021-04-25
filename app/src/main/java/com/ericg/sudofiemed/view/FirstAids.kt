package com.ericg.sudofiemed.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.adapter.FirstAidsAdapter
import com.ericg.sudofiemed.constants.Constants.permissions
import com.ericg.sudofiemed.constants.Constants.requestCode
import com.ericg.sudofiemed.databinding.FragmentFirstAidsBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.requestAppPermissions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.model.FirstAidData
import java.util.*

class FirstAids : Fragment(), SearchView.OnQueryTextListener, FirstAidsAdapter.ItemClick {
    private var firstAidsBinding: FragmentFirstAidsBinding? = null
    private lateinit var firstAidsAdapter: FirstAidsAdapter
    private var listOfFirstAidData: ArrayList<FirstAidData> = arrayListOf()
    private var searchedFirstAidData: ArrayList<FirstAidData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstAidsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first_aids, container, false)

        listOfFirstAidData = arrayListOf(
            FirstAidData(
                "Asphyxia",
                R.drawable.asphyxia,
                getString(R.string.desc_asphyxia)
            ),
            FirstAidData(
                "Epilepsy",
                R.drawable.epilepsy,
                getString(R.string.desc_epilepsy)
            ),
            FirstAidData(
                "Head ache",
                R.drawable.head_ache,
                getString(R.string.desc_head_ache)
            ),
            FirstAidData(
                "Head injury",
                R.drawable.head_injury,
                getString(R.string.desc_head_injury)
            ),
            FirstAidData(
                "Stroke",
                R.drawable.stroke,
                getString(R.string.desc_stroke)
            ),
            FirstAidData(
                "Drug overdose",
                R.drawable.drug_overdose,
                getString(R.string.desc_drug_overdose)
            ), FirstAidData(
                "Faint",
                R.drawable.faint,
                getString(R.string.desc_faint)
            ),
            FirstAidData(
                "Burn",
                R.drawable.hand_burn,
                getString(R.string.desc_burn)
            ),
            FirstAidData(
                "Heartburn",
                R.drawable.heart_burn,
                getString(R.string.desc_heart_burn)
            ),

            FirstAidData(
                "Fracture",
                R.drawable.fracture,
                getString(R.string.desc_fracture)
            ),
            FirstAidData(
                "Poisoning",
                R.drawable.poisoning,
                getString(R.string.desc_poisoning)
            ),
            FirstAidData(
                "More",
                R.drawable.first_aid_kit,
                "..."
            )
        )

        firstAidsAdapter = FirstAidsAdapter(listOfFirstAidData, this@FirstAids)

        searchFilter()

        firstAidsBinding!!.swipeToRefresh.let {
            it.setOnRefreshListener {
                it.onSwipe()
            }
        }

        requestAppPermissions(permissions, requestCode)

        onMenuIconClick(firstAidsBinding!!.menuIcon)
        return firstAidsBinding?.root?.apply {

            firstAidsBinding!!.firstAidsRecyclerview.apply {
                adapter = firstAidsAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }

    private fun onMenuIconClick(view: View) {
        firstAidsBinding?.menuIcon?.setOnClickListener {

            PopupMenu(requireContext(), view).apply {
                setOnMenuItemClickListener { item ->
                    when (item?.itemId) {

                        R.id.favorite -> {
                            // TODO keep this pending -> requires a lot of stuff!
                            toast("Favorites", Extensions.ToastDuration.SHORT)

                            true
                        }

                        R.id.settings -> {
                            findNavController().navigate(R.id.action_firstAids_to_settings)
                            true
                        }

                        R.id.shareApp -> {
                            val link = "https://googleplay.com/Ericg/sudofy-aid"
                            val advert: String =
                                "Have you heard of Sudofy First Aid App, well, this is an app that" +
                                        " guides you to perform first aids effectively whenever needed." +
                                        " download it from Google Play store $link"
                            Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, advert)
                            }.let {
                                startActivity(Intent.createChooser(it, "share via?"))
                            }
                            true
                        }
                        R.id.supportDev -> {
                            findNavController().navigate(R.id.action_firstAids_to_supportDeveloper)

                            true
                        }
                        else -> false
                    }
                }
                inflate(R.menu.main_menu)
                show()
            }
        }
    }

    override fun clickedItemDetails(view: View?, position: Int) {
        showDetails(firstAidsAdapter.firstAidsListData[position])
    }

    private fun showDetails(item: FirstAidData) {
        val action = FirstAidsDirections.actionFirstAidsToDetails(item)
        findNavController().navigate(action)
    }

    private fun searchFilter() {
        firstAidsBinding?.searchView!!.apply {
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@FirstAids)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query!!.trim().isNotEmpty()) {
            searchedFirstAidData = arrayListOf()

            listOfFirstAidData.forEach { firstAid ->
                if (firstAid.title.contains(query, true)) {
                    searchedFirstAidData.add(firstAid)
                }
                firstAidsAdapter.firstAidsListData = searchedFirstAidData
                firstAidsAdapter.notifyDataSetChanged()
            }
        } else {
            // data remains the same
            searchedFirstAidData = arrayListOf()
            firstAidsAdapter.firstAidsListData = listOfFirstAidData
            firstAidsAdapter.notifyDataSetChanged()

        }
        updateUI()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!!.trim().isNotEmpty()) {
            searchedFirstAidData = arrayListOf()
            searchedFirstAidData.clear()

            listOfFirstAidData.forEach { firstAid ->
                if (firstAid.title.trim().contains(newText, true)
                ) {
                    searchedFirstAidData.add(firstAid)
                    firstAidsAdapter.firstAidsListData = searchedFirstAidData
                    firstAidsAdapter.notifyDataSetChanged()

                } else {
                    searchedFirstAidData.clear()
                    firstAidsAdapter.firstAidsListData = listOfFirstAidData
                    firstAidsAdapter.notifyDataSetChanged()
                }
            }
        } else {
            searchedFirstAidData.clear()
            searchedFirstAidData = arrayListOf()
            firstAidsAdapter.firstAidsListData = listOfFirstAidData
            firstAidsAdapter.notifyDataSetChanged()

        }
        updateUI()
        return true
    }

    private fun updateUI() {
        firstAidsBinding?.imgNoData?.visibility =
            if (firstAidsAdapter.firstAidsListData.isEmpty()) {
                View.VISIBLE
            } else {
                INVISIBLE
            }
    }

    private fun SwipeRefreshLayout.onSwipe() {
        searchedFirstAidData.clear()
        firstAidsAdapter.firstAidsListData = listOfFirstAidData
        firstAidsAdapter.notifyDataSetChanged()
        updateUI()

        this.isRefreshing = false
    }
}
