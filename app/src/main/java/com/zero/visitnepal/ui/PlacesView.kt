package com.zero.visitnepal.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.zero.visitnepal.R
import com.zero.visitnepal.ui.home.adapter.HomePlacesAdapter
import kotlinx.android.synthetic.main.view_places.view.*

class PlacesView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.view_places, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PlacesView)
        places_tv.places_tv.text = attributes.getString(R.styleable.PlacesView_title)
        attributes.recycle()
    }

    fun setAdapter(homePlacesAdapter: HomePlacesAdapter) {
        places_list_rv.adapter = homePlacesAdapter
    }

    fun onClicked(resId: Int, bundle: Bundle) {
        places_cardview.setOnClickListener { findNavController().navigate(resId, bundle) }
    }
}