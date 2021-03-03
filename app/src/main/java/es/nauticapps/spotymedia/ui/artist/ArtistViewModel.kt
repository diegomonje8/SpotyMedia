package es.nauticapps.spotymedia.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.nauticapps.spotymedia.base.BaseState
import es.nauticapps.spotymedia.base.BaseViewModel
import es.nauticapps.spotymedia.datalayer.models.ArtistModel


class ArtistViewModel : BaseViewModel<ArtistListState>() {

    override val defaultState: ArtistListState = ArtistListState()


    override fun onStartFirstTime() {
    }

    fun getInformation(artist: ArtistModel) {
        updateToLoadingState(ArtistListState())

        executeCoroutines({
              updateToNormalState(ArtistListState(artist))
        }, { error ->
            updateToErrorState(ArtistListState(), error)
        })

    }



}