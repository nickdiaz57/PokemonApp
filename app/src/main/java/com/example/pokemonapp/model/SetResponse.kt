package com.example.pokemonapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SetResponse(
    @Json(name = "data")
    val sets : List<Set>?,
)