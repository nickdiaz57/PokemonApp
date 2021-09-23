package com.example.pokemonapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardResponse (
    val data: List<Card>?
        )