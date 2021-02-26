package es.nauticapps.spotymedia.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {

    private val state = MutableLiveData<BaseState>()
    fun getState(): LiveData<BaseState> = state

    fun getInformation(artist: ArtistModel) {
        state.postValue(BaseState.Loading())
        try {
            state.postValue(BaseState.Normal(ArtistListState(artist)))
            } catch (e: Exception) {
                state.postValue(BaseState.Error(e))
            }
    }

}