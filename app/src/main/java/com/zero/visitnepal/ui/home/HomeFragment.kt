package com.zero.visitnepal.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zero.visitnepal.R
import com.zero.visitnepal.databinding.FragmentHomeBinding
import com.zero.visitnepal.utils.ZoomOutPageTransformer


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.pager.adapter = HomeViewPagerAdapter()
        binding.indicator.setViewPager(binding.pager)
        binding.pager.setPageTransformer(ZoomOutPageTransformer())
        return binding.root
    }
}
