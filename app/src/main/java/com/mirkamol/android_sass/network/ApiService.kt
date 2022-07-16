package com.mirkamol.android_sass.network

import com.mirkamol.android_sass.model.HomePhotoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<HomePhotoItem>
}