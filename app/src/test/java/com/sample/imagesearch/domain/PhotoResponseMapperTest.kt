package com.sample.imagesearch.domain

import com.google.gson.Gson
import com.sample.imagesearch.domain.PhotoResponseMapper.mapToSimpleImage
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoResponseMapperTest {

    private lateinit var fakePhotoResponse: PhotoResponse

    @Before
    fun setUp() {
        val raw = this::class.java.getResource("/ApiResponseWithOneImage.json")?.readText() ?: "{}"
        fakePhotoResponse = Gson().fromJson(raw, PhotoResponse::class.java)
    }

    @Test
    fun `test photo mapper with one response`() {
        val simpleImageList = fakePhotoResponse.mapToSimpleImage()
        assertEquals(1, simpleImageList.size)
    }

    @Test
    fun `test photo mapper title`() {
        val expectedTitle = "Small Kitten 11-16-2021 DSC_5603"
        val simpleImageList = fakePhotoResponse.mapToSimpleImage()
        assertEquals(expectedTitle, simpleImageList.first().title)
    }

    @Test
    fun `test photo mapper url`() {
        val expectedUrl = "https://farm66.static.flickr.com/65535/51684801817_00428acdfc.jpg"
        val simpleImageList = fakePhotoResponse.mapToSimpleImage()
        assertEquals(expectedUrl, simpleImageList.first().url)
    }
}