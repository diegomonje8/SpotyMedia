package es.nauticapps.spotymedia.datalayer

import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import es.nauticapps.spotymedia.datalayer.network.SpotyNetwork

class SpotyRepository {

    suspend fun requestauth() : ArtistModel {
        return SpotyNetwork().requestAuth()
    }


    //suspend fun requestArtist(searchText: String): List<ArtistModel> {
    //    return MediaNetwork().requestMediaSearch(searchText).collection.items
    //}


}