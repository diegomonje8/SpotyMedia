package es.nauticapps.spotymedia.ui.tracks

import es.nauticapps.spotymedia.base.BaseViewState
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import es.nauticapps.spotymedia.datalayer.models.AlbumTracks
import java.io.Serializable

data class TracksListState(
    val listTracks : List<AlbumTracks> = listOf(),
    val album : AlbumItem? = null
):BaseViewState()


