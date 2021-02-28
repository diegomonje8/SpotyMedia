package es.nauticapps.spotymedia.ui.home
import android.provider.MediaStore
import es.nauticapps.spotymedia.base.BaseViewState
import es.nauticapps.spotymedia.datalayer.models.*
import java.io.Serializable


data class HomeListState  (

    val listArtists : List<ArtistModel> = listOf(),
    val listRelease : List<RealeaseItem>  = listOf(),
    val listFeatures : List<FeaturesItem> = listOf()

):BaseViewState()



