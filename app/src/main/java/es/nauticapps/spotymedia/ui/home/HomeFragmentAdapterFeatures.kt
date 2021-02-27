package es.nauticapps.spotymedia.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ItemHomeHorizontalBinding
import es.nauticapps.spotymedia.datalayer.models.FeaturesItem

class HomeFragmentAdapterFeatures (private var features: List<FeaturesItem>) : RecyclerView.Adapter<HomeFragmentAdapterFeatures.ViewHolder>() {

    class ViewHolder(val binding: ItemHomeHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemHomeHorizontalBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = features[position].images.firstOrNull()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        viewHolder.binding.homeFragmentHorizontalText.text = features[position].name

        Picasso.get()
            .load(myImage)
            .resize(80,80)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(viewHolder.binding.homeFragmentHorizontalImage)
    }


    override fun getItemCount() = features.size

    fun updateList (newList: List<FeaturesItem>) {
        features = newList
        notifyDataSetChanged()
    }

}