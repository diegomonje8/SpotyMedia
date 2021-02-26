package es.nauticapps.spotymedia.ui.artist.albums

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.nauticapps.spotymedia.R
import es.nauticapps.spotymedia.databinding.ItemArtistAlbumBinding
import es.nauticapps.spotymedia.datalayer.models.AlbumItem

class ArtistAlbumFragmentAdapter (private var albums: List<AlbumItem>) : RecyclerView.Adapter<ArtistAlbumFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemArtistAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemArtistAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var myImage = "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        try  {
            myImage = albums[position].images.firstOrNull()?.url ?: "https://images-assets.nasa.gov/image/PIA17669/PIA17669~thumb.jpg"
        } catch(e: Exception ) {  Log.e ("Spoty Media Error", "Error catching image")  }

        viewHolder.binding.albumTextName.text = albums[position].name
        viewHolder.binding.albumTextArtists.text = getAllArtists(position)
        viewHolder.binding.albumDateText.text = albums[position].release_date

        Picasso.get()
            .load(myImage)
            .resize(120,120)
            .centerCrop()
            .placeholder(R.drawable.ic_extraterrestre)
            .into(viewHolder.binding.albumTrackImage)

    }

    private fun getAllArtists(position: Int) : String {
        var result = ""
        albums[position].artists.forEach {
            result = if (result.isEmpty()) it.name
            else "$result & $it.name"
        }
        return result
    }

    override fun getItemCount() = albums.size

    fun updateList (newList: List<AlbumItem>) {
        albums = newList
        notifyDataSetChanged()
    }

}