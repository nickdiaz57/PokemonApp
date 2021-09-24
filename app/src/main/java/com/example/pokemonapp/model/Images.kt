package com.example.pokemonapp.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Images(
    val large: String?,
    val small: String?
) : Parcelable