package com.pyloa.fastnews.articles

import com.pyloa.fastnews.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(isLoading = true))
    val articlesState: MutableStateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(5000)
            _articlesState.emit(ArticlesState(error = "Error"))
            delay(2000)
            val fetchedArticles = fetchArticles()
            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    private fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            title = "Bob marley died!",
            desc = "Apparently Bob Marley died in a car accident.",
            date = "date1",
            imageUrl = "imageUrl1"
        ),
        Article(
            title = "President of the United States died",
            desc = "Recently reported that the president of the United States has died.",
            date = "date2",
            imageUrl = "imageUrl2"
        ),
        Article(
            title = "title3",
            desc = "desc3",
            date = "date3",
            imageUrl = "imageUrl3"
        )
    )
}