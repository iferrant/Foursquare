package com.betsson.foursquare.data.remote

import com.betsson.foursquare.BuildConfig
import com.betsson.foursquare.data.remote.model.NetworkSearch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitFsNetworkApi {
    @GET(value = "places/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("categories") categories: String,
    ) : NetworkResponse<NetworkSearch>
}


private data class NetworkResponse<T> (
    val data: T,
)

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
                .build()
        )
        .build()
        .create(RetrofitFsNetworkApi::class.java)

    override suspend fun search(query: String, categories: String): NetworkSearch =
        networkApi.search(query, categories).data
}
