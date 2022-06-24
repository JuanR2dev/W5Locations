package com.syllabus.w5.repository.remote.dominos.server.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "Stores")
    val stores: List<Store> = emptyList()
)