package es.nauticapps.spotymedia.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.base.BaseViewModel
import es.nauticapps.spotymedia.datalayer.SpotyRepository
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class TracksViewModel: BaseViewModel<TracksListState>() {

    override val defaultState: TracksListState = TracksListState()

    override fun onStartFirstTime() {}

    fun requestAlbums(album: AlbumItem) {
        updateToLoadingState(TracksListState(listOf()))

        executeCoroutines({
            val listResult = SpotyRepository().requestAlbumTracks(album.id)
            updateToNormalState(TracksListState(listResult, album))
        } , { error ->
            updateToErrorState(TracksListState(listOf()), error)
        })

    }

}