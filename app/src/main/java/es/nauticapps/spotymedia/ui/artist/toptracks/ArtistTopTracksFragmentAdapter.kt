package es.nauticapps.spotymedia.ui.artist.toptracks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ItemArtistToptrackBinding
import es.nauticapps.spotymedia.datalayer.models.Track

class ArtistTopTracksFragmentAdapter(private var tracks: List<Track>) : RecyclerView.Adapter<ArtistTopTracksFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemArtistToptrackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemArtistToptrackBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = tracks[position].album.images.firstOrNull()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        viewHolder.binding.artTextName.text = tracks[position].name

        Picasso.get()
            .load(myImage)
            .resize(120,120)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(viewHolder.binding.artTopTrackImage)

    }

    override fun getItemCount() = tracks.size

    fun updateList (newList: List<Track>) {
        tracks = newList
        notifyDataSetChanged()
    }



}