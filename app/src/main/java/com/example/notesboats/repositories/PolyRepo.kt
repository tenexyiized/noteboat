package com.example.notesboats.repositories

import com.example.notesboats.network.apis.PolyApi
import com.example.notesboats.network.responses.PolyResponse

class PolyRepo(val api:PolyApi) {

    suspend fun getDynamicList(): PolyResponse{
        return api.getDynamicList()
    }



}