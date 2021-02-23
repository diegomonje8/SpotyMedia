package es.nauticapps.spotymedia.datalayer.network

import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotyService {

    @GET("search?q=sun")
    suspend fun requestAuth(): ArtistModel

    @GET("search")
    suspend fun getArtists(@Query("q") searchText: String): ArtistModel

}