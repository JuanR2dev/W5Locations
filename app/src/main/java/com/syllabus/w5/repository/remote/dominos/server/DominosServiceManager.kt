package com.syllabus.w5.repository.remote.dominos.server

class DominosServiceManager(
    private val dominosService: DominosService
) {
    suspend fun search(zipCode: String) = kotlin.runCatching {
        dominosService.search(zipCode)
    }
}