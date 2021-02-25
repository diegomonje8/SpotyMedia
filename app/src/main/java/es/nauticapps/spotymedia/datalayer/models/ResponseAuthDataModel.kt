package es.nauticapps.spotymedia.datalayer.models

data class ResponseAuthDataModel (
    val access_token : String,
    val token_type: String,
    val expires_in: Int,
    val scope: String

)
