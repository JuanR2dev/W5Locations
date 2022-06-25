package com.syllabus.w5.repository.remote.dominos.server.response

import androidx.annotation.Keep
import com.google.android.gms.location.Granularity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SearchResponse(
    val Stores: List<Store> = emptyList()
)