package es.nauticapps.spotymedia.ui.artist.toptracks

import es.nauticapps.spotymedia.base.BaseViewModel
import es.nauticapps.spotymedia.datalayer.SpotyRepository

class ArtistTopTrackViewModel  : BaseViewModel<ArtistTopTrackListState>() {

    override val defaultState: ArtistTopTrackListState = ArtistTopTrackListState()


    override fun onStartFirstTime() {

    }


    fun requestTopTracks(artistId: String) {

        updateToLoadingState(ArtistTopTrackListState(listOf()))

        executeCoroutines({
            val listResult = SpotyRepository().requestArtistTopTracks(artistId)
            updateToNormalState(ArtistTopTrackListState(listResult))
        } , { error ->
            updateToErrorState(ArtistTopTrackListState(listOf()), error)

        })

    }



}