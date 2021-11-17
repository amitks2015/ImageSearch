package com.sample.imagesearch.domain

import java.io.Serializable

//Used by the application to load the images
data class FlickrImage(val title: String, val url: String): Serializable