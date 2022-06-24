package com.syllabus.w5.repository.remote.dominos.server

import com.syllabus.w5.repository.remote.dominos.server.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DominosRetrofitService {
    @GET("power/store-locator")
    suspend fun search(
        @Query("s")
        zipCode: String
    ): Response<SearchResponse>
}