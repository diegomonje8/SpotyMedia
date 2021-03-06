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

        viewHolder.binding.artTopTrackNameText.text = tracks[position].name
        viewHolder.binding.artTopTrackAlbumNameText.text = tracks[position].album.name
        viewHolder.binding.artTopTrackDurationText.text = getTrackDuration(tracks[position].duration_ms)
        viewHolder.binding.artTopTrackRankingText.text = tracks[position].popularity.toString()

        Picasso.get()
            .load(myImage)
            .resize(120,120)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(viewHolder.binding.artTopTrackImage)

    }

    private fun getTrackDuration(miliseconds: Int) : String {

        val seconds = (miliseconds / 1000) % 60
        val minutes = (miliseconds/ (1000*60)) % 60
        return "$minutes' : $seconds\""
    }

    override fun getItemCount() = tracks.size

    fun updateList (newList: List<Track>) {
        tracks = newList
        notifyDataSetChanged()
    }



}