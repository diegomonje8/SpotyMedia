package es.nauticapps.spotymedia.datalayer

import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.auth.SpotyNetworkAuth
import es.nauticapps.spotymedia.datalayer.models.SearchModel
import es.nauticapps.spotymedia.datalayer.network.SpotyNetwork

class SpotyRepository {

    //fun requestAuth() {
    //    SpotyNetworkAuth().getAuthToken()
    //}


    suspend fun requestArtist(): SearchModel {
         return SpotyNetwork().getSearch()
    }


}