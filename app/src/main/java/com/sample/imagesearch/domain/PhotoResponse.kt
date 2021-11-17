package com.sample.imagesearch.domain

import java.io.Serializable

//Response received by the API
data class PhotoResponse(val photos: Photos): Serializable

data class Photos(val page: Int, val perpage: Int, val photo: List<Photo>)

data class Photo(val id: String,
                 val owner: String,
                 val secret: String,
                 val server: String,
                 val farm: Int,
                 val title: String,
                 val ispublic: Int,
                 val isfriend: Int,
                 val isfamily: Int
): Serializable