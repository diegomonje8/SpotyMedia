package es.nauticapps.spotymedia.datalayer.auth.models

data class ResponseAuthDataModel (
    val access_token : String,
    val token_type: String,
    val expires_in: Int,
    val scope: String

)
