package es.nauticapps.spotymedia.datalayer.models

import java.io.Serializable

data class ReleaseModel(
    val albums: Albums
)

data class Albums(
    val href: String,
    val items: List<RealeaseItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class RealeaseItem(
    val album_type: String,
    val artists: List<Artist>,
    val available_markets: List<String>,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val type: String,
    val uri: String
):Serializable




