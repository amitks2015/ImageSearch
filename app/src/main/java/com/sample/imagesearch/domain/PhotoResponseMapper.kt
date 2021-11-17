package com.sample.imagesearch.domain

object PhotoResponseMapper {
    fun PhotoResponse.mapToSimpleImage(): List<FlickrImage> {
        return photos.photo.map {
            FlickrImage(
                title = it.title,
                url = "https://farm${it.farm}.static.flickr.com/${it.server}/${it.id}_${it.secret}.jpg"
            )
        }
    }
}