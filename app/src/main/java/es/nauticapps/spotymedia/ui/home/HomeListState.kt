package es.nauticapps.spotymedia.ui.home
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import java.io.Serializable

data class HomeListState  (
    
    val listArtists: ArtistModel = ArtistModel()

):Serializable