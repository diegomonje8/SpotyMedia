package es.nauticapps.spotymedia.ui.home
import android.provider.MediaStore
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.models.ArtistsResponse
import es.nauticapps.spotymedia.datalayer.models.SearchModel
import java.io.Serializable


data class HomeListState  (

    val listArtists : List<ArtistModel> = listOf()

):Serializable



