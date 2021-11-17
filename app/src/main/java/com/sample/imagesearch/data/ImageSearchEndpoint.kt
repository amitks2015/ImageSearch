package com.sample.imagesearch.data

import com.sample.imagesearch.API_KEY
import com.sample.imagesearch.domain.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchEndpoint {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=kittens&api_key=$API_KEY")
    suspend fun fetchPhotos(@Query("page") page: Int): Response<PhotoResponse>
}