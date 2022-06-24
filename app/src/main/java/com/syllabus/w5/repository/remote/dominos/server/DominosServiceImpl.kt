package com.syllabus.w5.repository.remote.dominos.server

import com.syllabus.w5.repository.remote.dominos.server.response.SearchResponse
import timber.log.Timber

class DominosServiceImpl(
    private val dominosRetrofitService: DominosRetrofitService
) : DominosService {
    override suspend fun search(zipCode: String): SearchResponse {
        Timber.d("Searching stores")
        dominosRetrofitService.search(zipCode).run {
            if (isSuccessful) return body() ?: throw Exception(errorBody()?.toString())
            else throw  Exception("Error calling API")
        }
    }
}