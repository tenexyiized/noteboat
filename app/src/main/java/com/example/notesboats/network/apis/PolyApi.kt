package com.example.notesboats.network.apis

import com.example.notesboats.network.responses.PolyResponse
import retrofit2.http.GET

interface PolyApi {

    @GET("dynamic-recycler-view/dynamic-list")
    suspend fun getDynamicList(): PolyResponse

}