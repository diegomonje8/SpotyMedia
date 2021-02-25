package es.nauticapps.spotymedia.datalayer.auth

import es.nauticapps.spotymedia.datalayer.models.ResponseAuthDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotyServiceAuth {

    @FormUrlEncoded
    @POST("/api/token")
    suspend fun getAuthToken(@Field("grant_type") grandType : String) : ResponseAuthDataModel

    @FormUrlEncoded
    @POST("/api/token")
    fun refreshToken(@Field("grant_type") grandType : String) : Call<ResponseAuthDataModel>





}