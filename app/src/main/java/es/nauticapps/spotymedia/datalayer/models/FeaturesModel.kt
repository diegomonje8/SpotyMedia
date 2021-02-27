package es.nauticapps.spotymedia.datalayer.models

import java.io.Serializable

data class FeaturesModel(
    val message: String,
    val playlists: Playlists
)

data class Playlists(
    val href: String,
    val items: List<FeaturesItem>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class FeaturesItem(
    val collaborative: Boolean,
    val description: String,
    val external_urls: ExternalUrlsFeatures,
    val href: String,
    val id: String,
    val images: List<ImageFeatures>,
    val name: String,
    val owner: Owner,
    val primary_color: Any,
    val `public`: Any,
    val snapshot_id: String,
    val tracks: Tracks,
    val type: String,
    val uri: String
):Serializable

data class ExternalUrlsFeatures(
    val spotify: String
)

data class ImageFeatures(
    val height: Any,
    val url: String,
    val width: Any
)

data class Owner(
    val display_name: String,
    val external_urls: ExternalUrlsXFeatures,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)

data class Tracks(
    val href: String,
    val total: Int
)

data class ExternalUrlsXFeatures(
    val spotify: String
)
