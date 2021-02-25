package es.nauticapps.spotymedia.datalayer.models


data class ArtistModel(
    val external_urls: ArtistExternalUrls,
    val followers: ArtistFollowers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<ArtistImage>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class ArtistExternalUrls(
    val spotify: String
)

data class ArtistFollowers(
    val href: Any,
    val total: Int
)

data class ArtistImage(
    val height: Int,
    val url: String,
    val width: Int
)



