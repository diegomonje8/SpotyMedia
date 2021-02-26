package es.nauticapps.spotymedia.ui.artist.related

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.datalayer.SpotyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ArtistRelatedViewModel : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState() : LiveData<BaseState> = state

    fun requestRelated(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.postValue(BaseState.Loading())
                val listResult = SpotyRepository().requestArtistRelated(artistId)
                state.postValue(BaseState.Normal(ArtistRelatedListState(listResult)))
            }catch(e: Exception) {
                state.postValue(BaseState.Error(e))
            }
        }
    }

}