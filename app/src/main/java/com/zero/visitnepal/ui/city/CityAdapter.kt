package com.zero.visitnepal.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zero.visitnepal.R
import com.zero.visitnepal.model.PlacesResponse
import com.zero.visitnepal.model.Result
import com.zero.visitnepal.utils.Constant
import kotlinx.android.synthetic.main.rv_city.view.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cityList = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_city,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = cityList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindItem(cityList[position])
    }

    fun setData(cityList: PlacesResponse) {
        this.cityList.clear()
        this.cityList.addAll(cityList.results)
        notifyDataSetChanged()
    }

    inner class CityViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val mPlaceName: TextView? = itemView.city_name
        private val mPlaceImage: ImageView? = itemView.city_image

        fun bindItem(result: Result) {
            mPlaceName?.text = result.name
            val photoReference: String = result.photos[0].photoReference
            val imageURL: String = Constant.IMAGE_URL + photoReference + Constant.IMAGE_KEY + Constant.API_KEY
            Picasso.get().load(imageURL).into(mPlaceImage)
        }
    }
}




