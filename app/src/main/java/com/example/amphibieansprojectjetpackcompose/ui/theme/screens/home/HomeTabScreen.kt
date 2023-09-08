package com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import com.example.amphibieansprojectjetpackcompose.model.UiState


@Composable
fun TabSingleItem(modifier: Modifier = Modifier, data: AphibeanItem) {
    Card(modifier = modifier.fillMaxWidth()) {
        Text(
            text = data.name,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun HomeTabScreen(modifier: Modifier = Modifier, data: List<AphibeanItem>, uiState: UiState, onItemClick : (AphibeanItem) -> Unit) {
    Row(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = data, key = { item -> item.name }) {
                TabSingleItem(data = it)
            }
        }
        DetailTabScreen(modifier = Modifier.weight(1f), data = uiState, onItemClick = { onItemClick(uiState.currentItem) })

    }
}

@Composable
fun DetailTabScreen(modifier: Modifier, data: UiState, onItemClick: (AphibeanItem) -> Unit) {
    Card(modifier = modifier.clickable {
        onItemClick(data.currentItem)
    }) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).crossfade(true)
                .data(data.currentItem.img_src).build(),
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .weight(1f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = data.currentItem.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )
    }
}
