package es.nauticapps.spotymedia.ui.artist.toptracks

import es.nauticapps.spotymedia.base.BaseViewState
import es.nauticapps.spotymedia.datalayer.models.Track
import java.io.Serializable


data class ArtistTopTrackListState(

    val listTopTracks : List<Track> = listOf()

):BaseViewState()
