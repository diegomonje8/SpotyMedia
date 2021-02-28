package es.nauticapps.spotymedia.ui.artist.albums

import es.nauticapps.spotymedia.base.BaseViewState
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import java.io.Serializable

data class ArtistAlbumListState(

     val listAlbums : List<AlbumItem> = listOf()

): BaseViewState()


