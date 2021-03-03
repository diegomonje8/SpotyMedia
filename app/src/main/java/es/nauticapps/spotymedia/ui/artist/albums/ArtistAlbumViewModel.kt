package es.nauticapps.spotymedia.ui.artist.albums

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

class ArtistAlbumViewModel : BaseViewModel<ArtistAlbumListState>() {

    override val defaultState: ArtistAlbumListState = ArtistAlbumListState()

    override fun onStartFirstTime() {}

    fun requestAlbums(artistId: String) {
        updateToLoadingState(ArtistAlbumListState(listOf()))

        executeCoroutines({
            val listResult = SpotyRepository().requestArtistAlbums(artistId)
            updateToNormalState(ArtistAlbumListState(listResult))
        } , { error ->
            updateToErrorState(ArtistAlbumListState(listOf()), error)
        })


    }



}