package org.smartmuseum.fortnitecompanion.networking

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Header
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.statement.HttpResponse
import org.smartmuseum.fortnitecompanion.config.AppConfig
import org.smartmuseum.fortnitecompanion.data.cosmetics.BannerResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.BeansCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CarsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.CosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.InstrumentsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.LegoKitsCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.NewCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.cosmetics.TracksCosmeticsResponse
import org.smartmuseum.fortnitecompanion.data.creator_code.CreatorCodeResponse
import org.smartmuseum.fortnitecompanion.data.map.MapResponse
import org.smartmuseum.fortnitecompanion.data.news.BrNewsResponse
import org.smartmuseum.fortnitecompanion.data.news.NewsResponse
import org.smartmuseum.fortnitecompanion.data.news.StwNewsResponse
import org.smartmuseum.fortnitecompanion.data.playlists.PlaylistsResponse
import org.smartmuseum.fortnitecompanion.data.stats.PlayerStatsResponse

const val BASE_URL = "https://fortnite-api.com/"
val API_KEY = AppConfig.FORTNITE_API_KEY

interface FortniteApiInterface {
//    @GET("v1/banners")
//    suspend fun getBanners(@Query("language") language: String? = null): NetworkResult<BannerResponse>

    @GET("v1/banners")
    suspend fun getBanners(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics")
//    suspend fun getCosmetics(@Query("language") language: String? = null): NetworkResult<CosmeticsResponse>

    @GET("v2/cosmetics")
    suspend fun getCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/new")
//    suspend fun getNewCosmetics(@Query("language") language: String? = null): NetworkResult<NewCosmeticsResponse>

    @GET("v2/cosmetics/new")
    suspend fun getNewCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/br")
//    suspend fun getBattleRoyaleCosmetics(@Query("language") language: String? = null): NetworkResult<CosmeticsResponse>

    @GET("v2/cosmetics/br")
    suspend fun getBattleRoyaleCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/tracks")
//    suspend fun getTracksCosmetics(@Query("language") language: String? = null): NetworkResult<TracksCosmeticsResponse>

    @GET("v2/cosmetics/tracks")
    suspend fun getTracksCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/instruments")
//    suspend fun getInstrumentsCosmetics(@Query("language") language: String? = null): NetworkResult<InstrumentsCosmeticsResponse>

    @GET("v2/cosmetics/instruments")
    suspend fun getInstrumentsCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/cars")
//    suspend fun getCarsCosmetics(@Query("language") language: String? = null): NetworkResult<CarsCosmeticsResponse>

    @GET("v2/cosmetics/cars")
    suspend fun getCarsCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/lego")
//    suspend fun getLegoCosmetics(@Query("language") language: String? = null): NetworkResult<LegoCosmeticsResponse>

    @GET("v2/cosmetics/lego")
    suspend fun getLegoCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/lego/kits")
//    suspend fun getLegoKitsCosmetics(@Query("language") language: String? = null): NetworkResult<LegoKitsCosmeticsResponse>

    @GET("v2/cosmetics/lego/kits")
    suspend fun getLegoKitsCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/beans")
//    suspend fun getBeanCosmetics(@Query("language") language: String? = null): NetworkResult<BeansCosmeticsResponse>

    @GET("v2/cosmetics/beans")
    suspend fun getBeanCosmetics(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/cosmetics/br/{cosmetic-id}")
//    suspend fun getBattleRoyaleCosmeticById(@Path("cosmetic-id") cosmeticId: String, @Query("language") language: String? = null): NetworkResult<CosmeticsResponse>

    @GET("v2/cosmetics/br/{cosmetic-id}")
    suspend fun getBattleRoyaleCosmeticById(@Path("cosmetic-id") cosmeticId: String, @Query("language") language: String? = null): HttpResponse

//    @GET("v1/map")
//    suspend fun getBattleRoyaleMap(@Query("language") language: String? = null): NetworkResult<MapResponse>

    @GET("v1/map")
    suspend fun getBattleRoyaleMap(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/news")
//    suspend fun getNews(@Query("language") language: String? = null): NetworkResult<NewsResponse>

    @GET("v2/news")
    suspend fun getNews(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/news/br")
//    suspend fun getBattleRoyaleNews(@Query("language") language: String? = null): NetworkResult<BrNewsResponse>

    @GET("v2/news/br")
    suspend fun getBattleRoyaleNews(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/news/stw")
//    suspend fun getStwNews(@Query("language") language: String? = null): NetworkResult<StwNewsResponse>

    @GET("v2/news/stw")
    suspend fun getStwNews(@Query("language") language: String? = null): HttpResponse

//    @GET("v2/news/creatorcode")
//    suspend fun getCreatorCode(@Query("language") language: String? = null): NetworkResult<CreatorCodeResponse>

    @GET("v2/news/creatorcode")
    suspend fun getCreatorCode(@Query("language") language: String? = null): HttpResponse

//    @GET("v1/playlists")
//    suspend fun getPlaylists(@Query("language") language: String? = null): NetworkResult<PlaylistsResponse>

    @GET("v1/playlists")
    suspend fun getPlaylists(@Query("language") language: String? = null): HttpResponse

//    @GET("v1/playlists/{playlist-id}")
//    suspend fun getPlaylistById(@Path("playlist-id") playlistId: String, @Query("language") language: String? = null): NetworkResult<PlaylistsResponse>

    @GET("v1/playlists/{playlist-id}")
    suspend fun getPlaylistById(@Path("playlist-id") playlistId: String, @Query("language") language: String? = null): HttpResponse

//    @GET("v2/stats/br/")
//    suspend fun getBattleRoyaleStats(
//        @Header("Authorization") authorization: String = API_KEY,
//        @Query("name") name: String,
//        @Query("language") language: String? = null): NetworkResult<PlayerStatsResponse>

    @GET("v2/stats/br/")
    suspend fun getBattleRoyaleStats(
        @Header("Authorization") authorization: String = API_KEY,
        @Query("name") name: String,
        @Query("language") language: String? = null): HttpResponse
}