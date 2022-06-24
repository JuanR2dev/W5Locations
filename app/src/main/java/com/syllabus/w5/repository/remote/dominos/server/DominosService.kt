package com.syllabus.w5.repository.remote.dominos.server

import com.syllabus.w5.repository.remote.dominos.server.response.SearchResponse

interface DominosService {
    suspend fun search(zipCode: String): SearchResponse
}