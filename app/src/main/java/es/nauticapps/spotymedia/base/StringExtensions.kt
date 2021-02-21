package es.nauticapps.spotymedia.base

import java.text.SimpleDateFormat

fun String.prettyDate(): String {
    return try {
        val rawFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val prettyFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = rawFormat.parse(this)
        val result = prettyFormat.format(date)
        result

    }
    catch (e: Exception) {
        this
    }
}