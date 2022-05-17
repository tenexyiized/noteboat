package com.example.notesboats.network.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PolyResponse(
    @Json(name = "data")
    val `data`: List<Data>
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "author")
        val author: String?,
        @Json(name = "banner-url")
        val bannerUrl: String?,
        @Json(name = "contacts")
        val contacts: List<Contact>?,
        @Json(name = "grid-urls")
        val gridUrls: List<String>?,
        @Json(name = "image-urls")
        val imageUrls: List<String>?,
        @Json(name = "quote")
        val quote: String?,
        @Json(name = "type")
        val type: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Contact(
            @Json(name = "avatar")
            val avatar: String,
            @Json(name = "city")
            val city: String,
            @Json(name = "id")
            val id: String,
            @Json(name = "name")
            val name: String
        )
    }
}