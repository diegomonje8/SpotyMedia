package es.nauticapps.spotymedia.ui.artist

import es.nauticapps.spotymedia.base.BaseViewState
import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import java.io.Serializable

data class ArtistListState (
    var artist: ArtistModel? = null
): BaseViewState()
