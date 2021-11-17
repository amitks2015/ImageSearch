package com.sample.imagesearch.domain

import com.google.gson.Gson
import com.sample.imagesearch.data.ImageSearchEndpoint
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class ImageSearchRepositoryTest {

    private val imageSearchEndpoint = mockk<ImageSearchEndpoint>()

    private lateinit var fakePhotoResponse: PhotoResponse

    @Before
    fun setup() {
        val raw = this::class.java.getResource("/ApiResponseWithMultipleImages.json")?.readText() ?: "{}"
        fakePhotoResponse = Gson().fromJson(raw, PhotoResponse::class.java)
    }

    @Test
    fun `test api success response`() {
        coEvery { imageSearchEndpoint.fetchPhotos(page = 1) } returns Response.success(fakePhotoResponse)
        val imageSearchRepository = ImageSearchRepository(imageSearchEndpoint)
        runBlocking {
            val response = imageSearchRepository.fetchPhotos(page = 1)
            assertTrue(response is NetworkResponse.Success)
        }
    }

    @Test
    fun `test api response with valid data`() {
        coEvery { imageSearchEndpoint.fetchPhotos(page = 1) } returns Response.success(fakePhotoResponse)
        val imageSearchRepository = ImageSearchRepository(imageSearchEndpoint)
        runBlocking {
            val response = imageSearchRepository.fetchPhotos(page = 1)
            val photos = response.data
            assertNotNull(photos)
            assertEquals(3, photos?.size?:0)
        }
    }

}