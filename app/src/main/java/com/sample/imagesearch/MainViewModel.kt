package com.sample.imagesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.imagesearch.domain.FlickrImage
import com.sample.imagesearch.domain.ImageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(imageSource: ImageSource): ViewModel() {

    val imageStateFlow: Flow<PagingData<FlickrImage>> = Pager(PagingConfig(50)) {
        imageSource
    }.flow.cachedIn(viewModelScope)

}