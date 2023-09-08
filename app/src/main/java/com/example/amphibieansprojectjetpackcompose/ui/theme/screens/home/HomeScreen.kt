package com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home

import android.text.style.TabStopSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibieansprojectjetpackcompose.R
import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import com.example.amphibieansprojectjetpackcompose.nertwork.NetworkState
import com.example.amphibieansprojectjetpackcompose.ui.theme.MainScreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
    viewModel: HomeViewModel
) {
    val networkState by viewModel.networkState.collectAsState()
    when (networkState) {
        is NetworkState.Success -> {
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
                    CompactView(data = (networkState as NetworkState.Success).data)
                }

                WindowWidthSizeClass.Medium -> {
                    HomeTabScreen(
                        networkState = (networkState as NetworkState.Success).currentItem,
                        data = (networkState as NetworkState.Success).data,
                        onItemClick = {
                            viewModel.updateCurrentItem(it)
                        })
                }

                WindowWidthSizeClass.Expanded -> {
                    HomeTabScreen(
                        networkState = (networkState as NetworkState.Success).currentItem,
                        data = (networkState as NetworkState.Success).data,
                        onItemClick = {
                            viewModel.updateCurrentItem(it)
                        })
                }

                else -> {
                    CompactView(data = (networkState as NetworkState.Success).data)
                }
            }
        }

        is NetworkState.Loading -> {
            LoadingErrorScreen(text = "Loading")
        }

        is NetworkState.Error -> {
            LoadingErrorScreen(text = (networkState as NetworkState.Error).error)
        }
    }

}

@Composable
fun CompactView(data: List<AphibeanItem>) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = data, key = { item -> item.name }) {
            AmphebianItem(data = it)
        }
    }
}

@Composable
fun LoadingErrorScreen(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleLarge)
}

@Composable
fun AmphebianItem(modifier: Modifier = Modifier, data: AphibeanItem) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column() {
            Text(
                text = data.name, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(10.dp),
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .crossfade(true)
                    .data(data.img_src)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = data.description, style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }


    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    MainScreen()
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItem(
    data: AphibeanItem = AphibeanItem(
        description = "Test Description",
        img_src = "",
        name = "Test Name",
        type = ""
    )
) {
    AmphebianItem(data = data)
}