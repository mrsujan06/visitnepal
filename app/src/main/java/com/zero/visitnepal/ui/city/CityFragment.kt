package com.zero.visitnepal.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zero.visitnepal.R
import com.zero.visitnepal.databinding.FragmentCityBinding

/**
 * A simple [Fragment] subclass.
 */
class CityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCityBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        return binding.root
    }
}
