package es.nauticapps.spotymedia.datalayer.auth




import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route




class RefreshTokenAuthenticate: Authenticator {

    lateinit var service: SpotyServiceAuth

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = refreshToken()
        return response.request.newBuilder().header("Authorization", "Bearer $token").build()
    }

    private fun refreshToken(): String {
        if (::service.isInitialized) {
            val myservice = service.refreshToken("client_credentials")
            try {
                val token = myservice
                if (token != null) {
                    return token.toString()
                }
            } catch(e: Exception) {
                return ""
            }
        }
        return ""
    }


}


