package es.nauticapps.spotymedia.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ItemListHomeBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel

class HomeFragmentAdapter(private var artists: List<ArtistModel>, private var listener: (artist: ArtistModel) -> Unit) : RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemListHomeBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = artists[position].images.firstOrNull()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        viewHolder.binding.artistName.text = artists[position].name

        Picasso.get()
                .load(myImage)
                .resize(120,120)
                .centerCrop()
                .placeholder(R.drawable.ic_extraterrestre)
                .into(viewHolder.binding.artistImage)

        viewHolder.itemView.setOnClickListener( View.OnClickListener {
            listener.invoke(artists[position])
        })



    }


    override fun getItemCount() = artists.size

    fun updateList (newList: List<ArtistModel>) {
        artists = newList
        notifyDataSetChanged()
    }

}