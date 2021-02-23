package es.nauticapps.spotymedia.datalayer.models

data class SearchModel(
    val artists: Artists
)

data class Artists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class Item(
    val external_urls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)

data class ExternalUrls(
    val spotify: String
)

data class Followers(
    val href: Any,
    val total: Int
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int
)
