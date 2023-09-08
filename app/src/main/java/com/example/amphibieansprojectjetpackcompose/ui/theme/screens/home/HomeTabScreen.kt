package com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem


@Composable
fun TabSingleItem(
    modifier: Modifier = Modifier,
    data: AphibeanItem,
    onItemClick: (AphibeanItem) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(data)
            }
            .height(150.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data.img_src)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.5f)
                    .size(60.dp, 60.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge)

            )
            Text(
                text = data.name,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(2f),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun HomeTabScreen(
    modifier: Modifier = Modifier,
    networkState: AphibeanItem,
    data: List<AphibeanItem>,
    onItemClick: (AphibeanItem) -> Unit
) {
    Row(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f), contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = data, key = { item -> item.name }) {
                TabSingleItem(data = it, onItemClick = { onItemClick(it) })
            }
        }
        DetailTabScreen(
            modifier = Modifier
                .weight(1f)
                .padding(15.dp), data = networkState
        )

    }
}

@Composable
fun DetailTabScreen(modifier: Modifier, data: AphibeanItem) {
    Card(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).crossfade(true)
                .data(data.img_src).build(),
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .weight(1f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = data.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(15.dp)
        )
    }
}
