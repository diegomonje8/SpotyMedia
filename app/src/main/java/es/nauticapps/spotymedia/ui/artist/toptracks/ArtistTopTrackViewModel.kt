package es.nauticapps.spotymedia.ui.artist.toptracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.datalayer.SpotyRepository
import es.nauticapps.spotymedia.ui.home.HomeListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistTopTrackViewModel  : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState() : LiveData<BaseState> = state

    fun requestTopTracks(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(BaseState.Loading())
                val listResult = SpotyRepository().requestArtistTopTracks(artistId)
                state.postValue(BaseState.Normal(ArtistTopTrackListState(listResult)))
            }catch(e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }

}