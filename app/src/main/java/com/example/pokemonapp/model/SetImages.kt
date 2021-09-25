package com.example.pokemonapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SetImages(
    val logo: String?,
    val symbol: String?
) : Parcelable