package com.pyloa.fastnews.articles

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
