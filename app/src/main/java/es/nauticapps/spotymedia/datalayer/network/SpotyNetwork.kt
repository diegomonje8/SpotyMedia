package es.nauticapps.spotymedia.datalayer.network

import es.nauticapps.spotymedia.datalayer.models.ArtistModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpotyNetwork {

    private lateinit var service : SpotyService


    private fun createRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://images-api.nasa.gov//")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(SpotyService::class.java)
    }

    suspend fun requestAuth() : ArtistModel {
        createRetrofit()
        return  service.requestAuth()
    }

    suspend fun requestArtist(searchText: String): ArtistModel {

        createRetrofit()
        return service.getArtists(searchText)

    }

}

