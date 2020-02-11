package com.zero.visitnepal.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zero.visitnepal.R
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.model.Result
import com.zero.visitnepal.utils.Constant
import kotlinx.android.synthetic.main.item_places_home.view.*
import timber.log.Timber

class HomePlacesAdapter : RecyclerView.Adapter<HomePlacesAdapter.HomePlacesViewHolder>() {

    private var placeList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePlacesViewHolder {
        return HomePlacesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_places_home,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: HomePlacesViewHolder, position: Int) {
        holder.bindItem(placeList[position])
    }

    fun setData(cityList: PlacesResponse) {
        placeList.clear()
        placeList.addAll(cityList.results)
        notifyDataSetChanged()
    }

    inner class HomePlacesViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun bindItem(result: Result) {
            itemView.item_places_name?.text = result.name
            val photoReference: String = result.photos?.get(0)?.photoReference ?: "null"
            val imageURL: String =
                Constant.IMAGE_URL + photoReference + Constant.IMAGE_KEY + Constant.API_KEY

            Picasso.get()
                .load(imageURL)
                .into(itemView.item_places_img, object : Callback {
                    override fun onSuccess() {
                        itemView.image_progress_bar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        Timber.e(e)
                    }

                })
        }
    }
}