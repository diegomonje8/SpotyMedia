package es.nauticapps.spotymedia.datalayer.network

import android.util.Log
import es.nauticapps.spotymedia.BuildConfig
import es.nauticapps.spotymedia.datalayer.auth.RefreshTokenAuthenticate
import es.nauticapps.spotymedia.datalayer.auth.SpotyNetworkAuth
import es.nauticapps.spotymedia.datalayer.models.SearchModel
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

    suspend fun getSearch() : SearchModel {

        val authToken = SpotyNetworkAuth().getAuthToken()
        //val authToken = "BQCSAXy_73ot6WtomJ1oDCeDPUjln9UXF5Y5ZjpIOvoHifUWrxc1r5NAa7KHJPVPttqNrPNJECPJGiFgbfQ"
        Log.e("TOKEN", authToken)
        loadRetrofit(authToken)
        val art = service.getArtists("clapton", "artist")
        return art
    }

}