package es.nauticapps.spotymedia.datalayer.models

import java.io.Serializable


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
):Serializable

data class ArtistExternalUrls(
    val spotify: String
):Serializable

data class ArtistFollowers(
    val href: Any,
    val total: Int
):Serializable

data class ArtistImage(
    val height: Int,
    val url: String,
    val width: Int
):Serializable



