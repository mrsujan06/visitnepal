package com.zero.visitnepal.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zero.visitnepal.R
import com.zero.visitnepal.model.Result
import kotlinx.android.synthetic.main.rv_home_parent.view.*

class HomeParentAdapter : RecyclerView.Adapter<HomeParentAdapter.ViewHolder>() {
    private var placeList = mutableListOf<Result>()
    private var attractionList = mutableListOf<Result>()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_home_parent, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 4
    }

    fun setData(placeList: List<Result>, attrationList: List<Result>) {
        this.placeList.clear()
        this.placeList.addAll(placeList)
        this.attractionList.addAll(attrationList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val childLayoutManager =
            LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = HomeChildAdapter()

            for (i in 0..3) {
                if (i == 0) {
                    (adapter as HomeChildAdapter).setData(placeList)
                } else if (i == 1) {
                    (adapter as HomeChildAdapter).setData(attractionList)
                }
            }
            setRecycledViewPool(viewPool)

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView = itemView.parent_child_rv

    }
}