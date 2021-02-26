package es.nauticapps.spotymedia.ui.artist.related

import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.models.Track
import java.io.Serializable

data class ArtistRelatedListState(

    val listRelated : List<ArtistModel> = listOf()

): Serializable
