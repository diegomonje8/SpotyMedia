package es.nauticapps.spotymedia.ui.home

import es.nauticapps.spotymedia.base.BaseViewModel
import es.nauticapps.spotymedia.datalayer.SpotyRepository

class HomeViewModel: BaseViewModel<HomeListState>() {

    override val defaultState: HomeListState = HomeListState()

    override fun onStartFirstTime() {
       requestArtist()
    }

    fun requestArtist(searchText: String = "jackson") {
        updateToLoadingState(HomeListState(listOf()))

        executeCoroutines({

            val listResult = SpotyRepository().requestArtist(searchText)
            val listResultRelease = SpotyRepository().requestRealeses()
            val listResultFeatures = SpotyRepository().requestFeatures()
            updateToNormalState(HomeListState(listResult, listResultRelease, listResultFeatures))

        } , { error ->

            updateToErrorState(HomeListState(listOf()), error)

        })
    }

}

