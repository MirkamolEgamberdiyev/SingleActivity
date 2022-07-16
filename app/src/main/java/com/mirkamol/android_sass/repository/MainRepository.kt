package com.mirkamol.android_sass.repository

import com.mirkamol.android_sass.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    ) {
    suspend fun getAllPhotos(page:Int, per_page:Int) = apiService.getPhotos(page, per_page)

}