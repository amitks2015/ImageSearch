package com.sample.imagesearch.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

//Create a PagingSource to implement infinite scroll
class ImageSource @Inject constructor(private val imageSearchRepository: ImageSearchRepository): PagingSource<Int, FlickrImage>() {

    override fun getRefreshKey(state: PagingState<Int, FlickrImage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrImage> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = imageSearchRepository.fetchPhotos(page = nextPage)
            LoadResult.Page(
                data = response.data?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if(response.data.isNullOrEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}