package es.nauticapps.spotymedia.datalayer.network

import es.nauticapps.spotymedia.BuildConfig
import es.nauticapps.spotymedia.datalayer.auth.RefreshTokenAuthenticate
import es.nauticapps.spotymedia.datalayer.auth.SpotyNetworkAuth
import es.nauticapps.spotymedia.datalayer.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotyNetwork {

    lateinit var service: SpotyService

    private fun loadRetrofit(authToken: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient(authToken))
            .build()
        service = retrofit.create(SpotyService::class.java)
    }

    private fun createHttpClient(authToken: String): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()
            chain.proceed(request)
        }

        builder.authenticator(RefreshTokenAuthenticate())
            .connectTimeout(90L, TimeUnit.SECONDS)
            .followRedirects(false)
            .followSslRedirects(false)

        return builder.build()

    }

    suspend fun getSearch(searchText: String) : SearchModel {

        val authToken = SpotyNetworkAuth().getAuthToken()
        loadRetrofit(authToken)
        return service.getArtists(searchText, "artist")
    }

    suspend fun getArtistsTopTracks(artistId: String) : TracksResponseModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getArtistTopTracks(artistId, "ES")

    }

    suspend fun getArtistsAlbums(artistId: String) : AlbumResponseModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getArtistAlbums(artistId, 50)

    }

    suspend fun getArtistsRelated(artistId: String) : RelatedArtistsModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getArtistRelated(artistId)

    }

    suspend fun getAlbumTracks(idAlbum: String) : TracksFromAlbumModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getAlbumTracks(idAlbum, 50)

    }

    suspend fun getReleases() : ReleaseModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getRealese()

    }

    suspend fun getFeatures(): FeaturesModel {

        loadRetrofit(SpotyNetworkAuth().getAuthToken())
        return service.getFeatures()

    }



}