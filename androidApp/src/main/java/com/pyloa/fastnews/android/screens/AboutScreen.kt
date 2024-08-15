package com.pyloa.fastnews.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pyloa.fastnews.Platform
import com.pyloa.fastnews.android.MyApplicationTheme

@Composable
fun AboutScreen(
    onBack: () -> Unit
) {
    Column {
        TopToolbar(onBack)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopToolbar(
    onTap: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "About") },
        navigationIcon = {
            IconButton(onClick = onTap) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }
        }
    )
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier
) {
    val itemsList = makeInfoList()

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(itemsList) { row ->
            RowView(
                title = row.first, subtitle = row.second
            )
        }

    }
}

private fun makeInfoList(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logInfo()

    return listOf(
        Pair("OS", "${platform.osName} ${platform.osVersion}"),
        Pair("Device model", platform.deviceModel),
        Pair("Density", "${platform.density}"),
    )
}

@Composable
private fun RowView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
    Divider()
}

@Preview
@Composable
private fun AboutScreenPreview() {
    MyApplicationTheme {
        AboutScreen(onBack = { })
    }
}