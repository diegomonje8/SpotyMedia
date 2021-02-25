package es.nauticapps.spotymedia.datalayer.network

import es.nauticapps.spotymedia.datalayer.models.RequestAuthDataModel
import es.nauticapps.spotymedia.datalayer.models.ResponseAuthDataModel
import es.nauticapps.spotymedia.datalayer.models.SearchModel
import retrofit2.http.*

interface SpotyService {


    @FormUrlEncoded
    @POST("/api/token")
    suspend fun getToken(@Field("grand_type") grand_type : RequestAuthDataModel): ResponseAuthDataModel


    @GET("search")
    suspend fun getArtists(@Query("q") searchText: String, @Query("type") artist: String): SearchModel

}