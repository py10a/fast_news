package com.pyloa.fastnews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform