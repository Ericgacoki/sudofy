package com.ericg.sudofiemed.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.adapter.FirstAidsAdapter
import com.ericg.sudofiemed.constants.Constants.permissions
import com.ericg.sudofiemed.constants.Constants.requestCode
import com.ericg.sudofiemed.databinding.DescriptionItemBinding
import com.ericg.sudofiemed.databinding.FragmentFirstAidsBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.requestAppPermissions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.model.FirstAidData
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        // TODO use the respective description

        listOfFirstAidData = arrayListOf(
            FirstAidData(
                "Asphyxia",
                ContextCompat.getDrawable(requireContext(), R.drawable.asphyxia_2),
                getString(R.string.desc_asphyxia)
            ),
            FirstAidData(
                "Epilepsy",
                ContextCompat.getDrawable(requireContext(), R.drawable.epilepsy_1),
                getString(R.string.desc_epilepsy)
            ),
            FirstAidData(
                "Head ache",
                ContextCompat.getDrawable(requireContext(), R.drawable.head_ache),
                getString(R.string.desc_head_ache)
            ),
            FirstAidData(
                "Head injury",
                ContextCompat.getDrawable(requireContext(), R.drawable.head_injury2),
                getString(R.string.desc_head_injury)
            ),
            FirstAidData(
                "Stroke",
                ContextCompat.getDrawable(requireContext(), R.drawable.stroke_1),
                getString(R.string.desc_stroke)
            ),
            FirstAidData(
                "Drug overdose",
                ContextCompat.getDrawable(requireContext(), R.drawable.drug_overdose2),
                getString(R.string.desc_drug_overdose)
            ),FirstAidData(
                "Faint",
                ContextCompat.getDrawable(requireContext(), R.drawable.faint_1),
                getString(R.string.desc_faint)
            ),
            FirstAidData(
                "Burn",
                ContextCompat.getDrawable(requireContext(), R.drawable.hand_burn2),
                getString(R.string.desc_burn)
            ),
            FirstAidData(
                "Heartburn",
                ContextCompat.getDrawable(requireContext(), R.drawable.heart_burn2),
                getString(R.string.desc_heart_burn)
            ),

            FirstAidData(
                "Fracture",
                ContextCompat.getDrawable(requireContext(), R.drawable.fracture),
                getString(R.string.desc_fracture)
            ),
            FirstAidData(
                "Poisoning",
                ContextCompat.getDrawable(requireContext(), R.drawable.poisoning_2),
                getString(R.string.desc_poisoning)
            ),
            FirstAidData(
                "More",
                ContextCompat.getDrawable(requireContext(), R.drawable.first_aid_kit),
                ""
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


    override fun clickedItemDetails(view: View?, position: Int) =
        showDescription(firstAidsAdapter.firstAidsListData[position])

    var loved = false
    private fun showDescription(item: FirstAidData) {
        val btmSheetDialog = BottomSheetDialog(requireContext())
        val btmSheetBinding: DescriptionItemBinding =
            DescriptionItemBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        // btmSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btmSheetDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(btmSheetBinding.root)
            setOnDismissListener {
                loved = false
            }
        }.create()

        btmSheetBinding.apply {
            descImage.apply {
                clipToOutline = true
                setImageDrawable(item.image)
            }
            descTitle.text = item.title
            descText.text = item.desc

            love.setOnClickListener {
                setLoveState(this.love)
            }
            share.setOnClickListener {
                share(item)
            }
        }

        btmSheetDialog.apply {
            setCancelable(true)
        }.show()
    }


    private fun setLoveState(imageView: ImageView) {
        imageView.setImageResource(
            if (loved) {
                loved = false
                R.drawable.ic_love
            } else {
                loved = true
                R.drawable.ic_loved
            }
        )
    }

    private fun share(item: FirstAidData) {
        // val imageUri = Uri.parse("android.resource:://${getPackageName()}/drawable/chock")
        // val image = BitmapFactory.decodeResource(resources,R.drawable.chock)
        // image.compress(Bitmap.CompressFormat.JPEG,100,Bytes)
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "${item.title} \n${item.desc}")
            // putExtra(Intent.EXTRA_STREAM, image)
            startActivity(Intent.createChooser(this, "Send via?"))
        }
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
        // firstAidsBinding?.imgNoData?.visibility = INVISIBLE
        updateUI()

        this.isRefreshing = false
    }
}