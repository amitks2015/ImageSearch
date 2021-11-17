package com.sample.imagesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.sample.imagesearch.domain.FlickrImage
import com.sample.imagesearch.ui.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchTheme {
                Scaffold(
                topBar = {
                    TopAppBar(
                        title = {Text(stringResource(id = R.string.app_name))},
                    )
                }
            ) {
                ImageGrid(imageStateFlow = viewModel.imageStateFlow)
            }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGrid(imageStateFlow: Flow<PagingData<FlickrImage>>) {
    val imageList = imageStateFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(3),
        content = {
            items(imageList.itemCount) { index ->
                PhotoCard(imageList[index])
            }
        }
    )
}

@Composable
fun PhotoCard(image: FlickrImage?) {
    Card(
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Red.copy(alpha = 0.5f)
        ),
        modifier = Modifier.size(130.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(data = image?.url),
            contentDescription = image?.title,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageSearchTheme {
        PhotoCard(
            image = FlickrImage(
                title = "",
                url = "https://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg"
            )
        )
    }
}