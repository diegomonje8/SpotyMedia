package es.nauticapps.spotymedia.datalayer.network

import es.nauticapps.spotymedia.datalayer.auth.models.RequestAuthDataModel
import es.nauticapps.spotymedia.datalayer.auth.models.ResponseAuthDataModel
import es.nauticapps.spotymedia.datalayer.models.*
import retrofit2.http.*

interface SpotyService {


    @GET("search")
    suspend fun getArtists(@Query("q") searchText: String, @Query("type") artist: String): SearchModel

    @GET( "artists/{id}/albums")
    suspend fun getArtistAlbums(@Path( "id") id : String, @Query("limit") limit: Int) : AlbumResponseModel

    @GET( "artists/{id}/related-artists")
    suspend fun getArtistRelated(@Path( "id") id : String,) : RelatedArtistsModel

    @GET( "artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(@Path( "id") id : String, @Query("market") market: String) : TracksResponseModel

    @GET( "albums/{id}/tracks")
    suspend fun getAlbumTracks(@Path( "id") id : String, @Query("limit") limit: Int) : TracksFromAlbumModel



}