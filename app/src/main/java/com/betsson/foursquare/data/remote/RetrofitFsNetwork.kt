package com.betsson.foursquare.data.remote

import com.betsson.foursquare.BuildConfig
import com.betsson.foursquare.data.remote.model.NetworkPhoto
import com.betsson.foursquare.data.remote.model.NetworkResult
import com.betsson.foursquare.data.remote.model.NetworkSearch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitFsNetworkApi {
    @GET(value = "places/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("fields") fields: String,
        @Query("categories") categories: String,
        @Query("limit") limit: Int,
    ) : NetworkSearch

    @GET(value = "places/{id}")
    suspend fun getPlace(
        @Path("id") query: String,
        @Query("fields") fields: String,
    ) : NetworkResult

    @GET(value = "places/{id}/photos")
    suspend fun getPhotos(
        @Path("id") query: String,
    ) : List<NetworkPhoto>
}


@Singleton
class RetrofitFsNetwork @Inject constructor() : FsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor {
                    val request = it
                        .request()
                        .newBuilder()
                        .addHeader("accept", "application/json")
                        .addHeader("Authorization", BuildConfig.AUTH_TOKEN)
                        .build()
                    it.proceed(request)
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitFsNetworkApi::class.java)

    override suspend fun search(
        query: String,
        fields: String,
        categories: String,
        limit: Int,
    ): NetworkSearch =
        networkApi.search(query, fields, categories, limit)

    override suspend fun getPlace(id: String, fields: String): NetworkResult =
        networkApi.getPlace(id, fields)

    override suspend fun getPhotos(id: String): List<NetworkPhoto> =
        networkApi.getPhotos(id)
}
