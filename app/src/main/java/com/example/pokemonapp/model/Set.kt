package com.example.pokemonapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Set(
    val id: String?,
    @Json(name = "images")
    val setImages: SetImages?,
    val name: String?,
    val printedTotal: Int?,
    val releaseDate: String?,
    val series: String?,
    val total: Int?,
    val updatedAt: String?
) : Parcelable