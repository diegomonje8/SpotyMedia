package es.nauticapps.spotymedia.datalayer.models


data class TracksFromAlbumModel (
    val href: String,
    val items: List<AlbumTracks>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class AlbumTracks (
    val artists: List<Artist>,
    val available_markets: List<String>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrlsX,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val name: String,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)






