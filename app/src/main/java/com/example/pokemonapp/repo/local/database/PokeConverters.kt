package com.example.pokemonapp.repo.local.database

import androidx.room.TypeConverter
import com.example.pokemonapp.model.Images
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class PokeConverters {

    private val moshi by lazy { Moshi.Builder().build() }

    private val stringListAdapter by lazy {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        return@lazy moshi.adapter<List<String>>(type)
    }

    private val intListAdapter by lazy {
        val type = Types.newParameterizedType(List::class.java, Integer::class.java)
        return@lazy moshi.adapter<List<Int>>(type)
    }

    @TypeConverter
    fun stringListToString(list: List<String>?) : String {
        return stringListAdapter.toJson(list)
    }

    @TypeConverter
    fun stringToStringList(data: String) : List<String>? {
        return stringListAdapter.fromJson(data)
    }

    @TypeConverter
    fun intListToString(list: List<Int>?) : String {
        return intListAdapter.toJson(list)
    }

    @TypeConverter
    fun stringToIntList(data: String) : List<Int>? {
        return intListAdapter.fromJson(data)
    }

    @TypeConverter
    fun imagesListToString(images: Images?) : String {
        return moshi.adapter(Images::class.java).toJson(images)
    }

    @TypeConverter
    fun stringToImagesList(data: String) : Images? {
        return moshi.adapter(Images::class.java).fromJson(data)
    }
}