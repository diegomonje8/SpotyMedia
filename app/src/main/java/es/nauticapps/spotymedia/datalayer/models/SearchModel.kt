package es.nauticapps.spotymedia.datalayer.models


data class SearchModel(
    val artists: ArtistsResponse
)

data class ArtistsResponse (
    val href: String,
    val items: List<ArtistModel>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)


