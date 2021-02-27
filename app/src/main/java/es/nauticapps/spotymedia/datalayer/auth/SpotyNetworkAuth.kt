package es.nauticapps.spotymedia.datalayer.auth

import android.util.Log
import es.nauticapps.spotymedia.BuildConfig
import es.nauticapps.spotymedia.datalayer.auth.models.RequestAuthDataModel
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpotyNetworkAuth {

    lateinit var service: SpotyServiceAuth

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
        service = retrofit.create(SpotyServiceAuth::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", Credentials.basic(BuildConfig.PUBLIC_KEY,BuildConfig.PRIVATE_KEY))
                .build()
            chain.proceed(request)
        }

        return builder.build()

    }

     suspend fun getAuthToken() : String {
        loadRetrofit()

        val client = RequestAuthDataModel("client_credentials")
        val response = service.getAuthToken(client.grant_type)
        Log.e("TOKEN", response.access_token)
        return response.access_token


    }


}