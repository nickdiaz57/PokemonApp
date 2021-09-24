package com.example.pokemonapp.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "poke_card_table")
@JsonClass(generateAdapter = true)
data class Card(
    @PrimaryKey val id: String?,
    val evolvesFrom: String?,
    val hp: String?,
    val images: Images?,
    val name: String?,
    val nationalPokedexNumbers: List<Int>?,
    val types: List<String>?
) : Parcelable