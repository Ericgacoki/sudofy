package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.adapter.ViewPager2Adapter
import com.ericg.sudofiemed.databinding.FragmentOnBoardViewPagerBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.toast

class OnBoardViewPager : Fragment() {
    private var onBoardViewPagerBinding: FragmentOnBoardViewPagerBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragments = arrayListOf(OnBoard1(), OnBoard2(), OnBoard3())
        val viewPagerAdapter = ViewPager2Adapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        onBoardViewPagerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_on_board_view_pager,
            container,
            false
        )
        return onBoardViewPagerBinding?.root?.apply {
            onBoardViewPagerBinding?.onBoardViewPager2?.let {
                it.adapter = viewPagerAdapter
                it.setOnDragListener { view, event ->
                    if (event.y < 1){
                        toast("+ve event captured", Extensions.ToastDuration.LONG)
                    }
                    true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoardViewPagerBinding = null
    }
}