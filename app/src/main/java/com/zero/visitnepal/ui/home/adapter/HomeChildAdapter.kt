package com.zero.visitnepal.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zero.visitnepal.R
import com.zero.visitnepal.model.Result
import com.zero.visitnepal.utils.Constant
import kotlinx.android.synthetic.main.rv_home_child.view.*

class HomeChildAdapter : RecyclerView.Adapter<HomeChildAdapter.ViewHolder>() {

    private var placeList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_home_child,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(placeList[position])
    }

    fun setData(placeList: List<Result>) {
        this.placeList.clear()
        this.placeList.addAll(placeList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mPlaceImage: ImageView = itemView.child_imageView
        private val mPlaceName: TextView = itemView.child_name

        fun bindItem(result: Result) {
            mPlaceName.text = result.name
            val photoReference: String = result.photos[0].photoReference
            val imageURL: String = Constant.IMAGE_URL + photoReference + Constant.IMAGE_KEY + Constant.API_KEY
            Picasso.get().load(imageURL).into(mPlaceImage)
        }

    }
}