package es.nauticapps.spotymedia.ui.tracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.datalayer.SpotyRepository
import es.nauticapps.spotymedia.datalayer.models.AlbumItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class TracksViewModel: ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState() : LiveData<BaseState> = state

    fun requestAlbums(album: AlbumItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(BaseState.Loading())
                val listResult = SpotyRepository().requestAlbumTracks(album.id)
                state.postValue(BaseState.Normal(TracksListState(listResult, album)))
            }catch(e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }

}