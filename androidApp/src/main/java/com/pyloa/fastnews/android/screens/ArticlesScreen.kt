package com.pyloa.fastnews.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pyloa.fastnews.articles.Article
import com.pyloa.fastnews.articles.ArticlesViewModel


@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticlesViewModel
) {
    val articlesState = viewModel.articlesState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppBar()
        if (articlesState.value.isLoading) {
            LoadingView()
        }
        if (articlesState.value.error != null) {
            ErrorView(articlesState.value.error!!)
        }
        if (articlesState.value.articles.isEmpty()) {
            EmptyView()
        }
        if (articlesState.value.articles.isNotEmpty()) {
            ArticlesListView(articlesState.value.articles)
        }
    }
}

@Composable
fun ArticlesListView(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .width(64.dp)
                .height(64.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.desc,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.date,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No articles",
        )
    }
}

@Composable
fun ErrorView(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
        )
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            Row {
                IconButton(onClick = {
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            }
        }
    )
}

// ********************* PREVIEW *********************
@Composable
@Preview
fun ArticlesScreenPreview() {
    ArticlesScreen(
        viewModel = ArticlesViewModel()
    )
}