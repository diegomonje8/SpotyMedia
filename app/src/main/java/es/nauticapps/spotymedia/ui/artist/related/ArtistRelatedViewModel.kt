package es.nauticapps.spotymedia.ui.artist.related

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.base.BaseViewModel
import es.nauticapps.spotymedia.datalayer.SpotyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistRelatedViewModel : BaseViewModel<ArtistRelatedListState>() {


    override val defaultState: ArtistRelatedListState = ArtistRelatedListState()

    override fun onStartFirstTime() {}

    fun requestRelated(artistId: String) {

        updateToLoadingState(ArtistRelatedListState(listOf()))

        executeCoroutines({
            val listResult = SpotyRepository().requestArtistRelated(artistId)
            updateToNormalState(ArtistRelatedListState(listResult))
        } , { error ->
            updateToErrorState(ArtistRelatedListState(listOf()),error)
        })
    }

}