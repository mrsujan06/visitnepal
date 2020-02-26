package com.zero.visitnepal.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zero.visitnepal.R
import com.zero.visitnepal.model.PlacesResult
import com.zero.visitnepal.utils.Constant
import kotlinx.android.synthetic.main.rv_city.view.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var placeList = mutableListOf<PlacesResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_city,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindItem(placeList[position])
    }

    fun setData(result: List<PlacesResult>) {
        placeList.clear()
        placeList.addAll(result)
        notifyDataSetChanged()
    }

    inner class CityViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val mPlaceName: TextView? = itemView.city_name
        private val mPlaceImage: ImageView? = itemView.city_image

        fun bindItem(result: PlacesResult) {
            mPlaceName?.text = result.name
            val photoReference: String = result.photos!![0].photoReference
            val imageURL: String = Constant.IMAGE_URL + photoReference + Constant.IMAGE_KEY + Constant.API_KEY
            Picasso.get().load(imageURL).into(mPlaceImage)
        }
    }
}




