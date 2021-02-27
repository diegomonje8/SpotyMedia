package es.nauticapps.spotymedia.datalayer

import es.nauticapps.spotymedia.datalayer.models.*
import es.nauticapps.spotymedia.datalayer.network.SpotyNetwork

class SpotyRepository {

    suspend fun requestArtist(searchText: String): List<ArtistModel> {
         return SpotyNetwork().getSearch(searchText).artists.items
    }

    suspend fun requestArtistTopTracks(artistId: String) : List<Track> {
        return SpotyNetwork().getArtistsTopTracks(artistId).tracks
    }

    suspend fun requestArtistAlbums(artistId: String) : List<AlbumItem> {
        return SpotyNetwork().getArtistsAlbums(artistId).items
    }

    suspend fun requestArtistRelated(artistId: String) : List<ArtistModel> {
        return SpotyNetwork().getArtistsRelated(artistId).artists
    }

    suspend fun requestAlbumTracks(albumId: String) : List<AlbumTracks> {
        return SpotyNetwork().getAlbumTracks(albumId).items
    }

    suspend fun requestRealeses() : List<RealeaseItem> {
        return SpotyNetwork().getReleases().albums.items
    }

    suspend fun requestFeatures() : List<FeaturesItem> {
        return SpotyNetwork().getFeatures().playlists.items
    }


}