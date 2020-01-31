package com.zero.visitnepal.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.zero.visitnepal.R
import com.zero.visitnepal.ui.home.adapter.HomePlacesAdapter
import kotlinx.android.synthetic.main.view_places.view.*

class PlacesView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val recyclerView: RecyclerView
    private val textView: TextView

    init {
        inflate(context, R.layout.view_places, this)
        textView = findViewById(R.id.places_tv)
        recyclerView = findViewById(R.id.places_list_rv)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PlacesView)
        textView.places_tv.text = attributes.getString(R.styleable.PlacesView_title)
        attributes.recycle()
    }

    fun setAdapter(homePlacesAdapter: HomePlacesAdapter) {
        recyclerView.adapter = homePlacesAdapter
    }
}