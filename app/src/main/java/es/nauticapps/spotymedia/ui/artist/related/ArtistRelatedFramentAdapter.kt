package es.nauticapps.spotymedia.ui.artist.related

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ItemArtistRelatedBinding
import es.nauticapps.spotymedia.datalayer.models.ArtistModel

class ArtistRelatedFramentAdapter (private var artists: List<ArtistModel>) : RecyclerView.Adapter<ArtistRelatedFramentAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemArtistRelatedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemArtistRelatedBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = artists[position].images.firstOrNull()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        viewHolder.binding.artTextName.text = artists[position].name
        viewHolder.binding.artTextGenres.text = getAllGenres(position)
        viewHolder.binding.artistFollowersText.text = artists[position].followers.total.toString()
        viewHolder.binding.artistRankingText.text = artists[position].popularity.toString()

        Picasso.get()
            .load(myImage)
            .resize(120,120)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(viewHolder.binding.relatedTrackImage)

    }

    private fun getAllGenres(position: Int) : String {
        var result = ""
        artists[position].genres.forEach {
            result = if (result.isEmpty()) it
            else "$result, $it"
        }
        return result
    }

    override fun getItemCount() = artists.size

    fun updateList (newList: List<ArtistModel>) {
        artists = newList
        notifyDataSetChanged()
    }



}