package es.nauticapps.spotymedia.ui.artist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.nauticapps.spotymedia.ui.artist.toptracks.ArtistTopTracksFragment

class ArtistViewPagerAdapter(fragment : Fragment, private val artistId : String) : FragmentStateAdapter(fragment)  {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArtistTopTracksFragment(artistId)
            1 -> ArtistTopTracksFragment(artistId)
            2 -> ArtistTopTracksFragment(artistId)
            else -> ArtistTopTracksFragment(artistId)
        }
    }


}