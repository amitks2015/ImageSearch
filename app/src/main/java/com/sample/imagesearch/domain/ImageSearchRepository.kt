package com.sample.imagesearch.domain

import com.sample.imagesearch.data.ImageSearchEndpoint
import com.sample.imagesearch.domain.PhotoResponseMapper.mapToSimpleImage
import javax.inject.Inject

class ImageSearchRepository @Inject constructor (private val imageSearchEndpoint: ImageSearchEndpoint): BaseRepository() {

    suspend fun fetchPhotos(page: Int): NetworkResponse<List<FlickrImage>> {
        val response =  safeApiCall {
            imageSearchEndpoint.fetchPhotos(page)
        }
        return when(response) {
            is NetworkResponse.Success -> {
                val imageList = (response.data as PhotoResponse).mapToSimpleImage()
                NetworkResponse.Success(data = imageList)
            }

            is NetworkResponse.Error -> {
                NetworkResponse.Error(message = response.message?:"")
            }

           is NetworkResponse.Loading -> {
               NetworkResponse.Loading()
           }
        }
    }

}