package es.nauticapps.spotymedia.ui.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.nauticapps.spotymedia.databinding.ItemTrackBinding
import es.nauticapps.spotymedia.datalayer.models.AlbumTracks

class TracksFragmentAdapter (private var tracks: List<AlbumTracks>) : RecyclerView.Adapter<TracksFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemTrackBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.binding.trackFragmentItemNum.text = (position + 1).toString()
        viewHolder.binding.trackFragmentItemSong.text = tracks[position].name
        viewHolder.binding.trackFragmentItemDuration.text = getTrackDuration(tracks[position].duration_ms)
    }

    private fun getTrackDuration(miliseconds: Int) : String {

        val seconds = (miliseconds / 1000) % 60
        val minutes = (miliseconds/ (1000*60)) % 60
        return "$minutes' : $seconds\""
    }

    override fun getItemCount() = tracks.size

    fun updateList (newList: List<AlbumTracks>) {
        tracks = newList
        notifyDataSetChanged()
    }

}