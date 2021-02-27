package es.nauticapps.spotymedia.ui.artist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import es.nauticapps.spotymedia.ui.artist.albums.ArtistAlbumFragment
import es.nauticapps.spotymedia.ui.artist.related.ArtistRelatedFragment
import es.nauticapps.spotymedia.ui.artist.toptracks.ArtistTopTracksFragment

class ArtistViewPagerAdapter(fragment : Fragment, private val artistId : String, private var listener: (Album: AlbumItem) -> Unit) : FragmentStateAdapter(fragment)  {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArtistTopTracksFragment(artistId)
            1 -> ArtistAlbumFragment(artistId, listener)
            2 -> ArtistRelatedFragment(artistId)
            else -> ArtistTopTracksFragment(artistId)
        }
    }


}