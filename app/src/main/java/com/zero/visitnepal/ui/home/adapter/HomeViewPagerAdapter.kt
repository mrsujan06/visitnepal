package com.zero.visitnepal.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zero.visitnepal.R
import kotlinx.android.synthetic.main.viewpager_home.view.*

class HomeViewPagerAdapter : RecyclerView.Adapter<HomeViewPagerAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewPagerAdapterViewHolder =
        HomeViewPagerAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewpager_home,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: HomeViewPagerAdapterViewHolder, position: Int) =
        holder.itemView.run {
            if (position == 0) {
                Picasso.get().load(R.drawable.vp_pokhara).into(viewpager_home_image)
            }
            if (position == 1) {
                Picasso.get().load(R.drawable.vp_ghorepani).into(viewpager_home_image)
            }
            if (position == 2) {
                Picasso.get().load(R.drawable.vp_lumbini).into(viewpager_home_image)
            }
            if (position == 3) {
                Picasso.get().load(R.drawable.vp_chandragiritemple).into(viewpager_home_image)
            }
        }
}

class HomeViewPagerAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)