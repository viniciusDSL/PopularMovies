package com.vinidsl.popular_movies_sdk.util

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    @Test
    fun getPosterUrl() {
        assertEquals("https://image.tmdb.org/t/p/w200123456.png",
            Utils.getPosterUrl("123456.png"))
    }
}