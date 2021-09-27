package com.example.pokemonapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.model.Queries
import com.example.pokemonapp.repo.PokeRepo
import com.example.pokemonapp.util.PageAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val pokeRepo: PokeRepo
) : ViewModel() {

    private val _cardList = MutableLiveData<List<Card>>()
    val cardList: LiveData<List<Card>> get() = _cardList
    var queries: Queries? = null
    var currentPageAction = PageAction.FIRST

    fun fetchCards(queries: Queries) {
        this.queries = queries
        fetchCards(PageAction.FIRST)
    }

    fun fetchCards(pageAction: PageAction) {
        currentPageAction = pageAction
        queries?.let { query -> query.page = pageAction.update(query.page ?: 1) }

        viewModelScope.launch {
            pokeRepo.getCards(queries).collect { cards ->
                _cardList.postValue(cards)
            }
        }
    }

//    fun fetchRandomCard() {
//        val counter = Random.nextInt(1, 13935)
//        viewModelScope.launch {
//            pokeRepo.getCards(counter, 1).collect { card ->
//                _cardList.postValue(card)
//            }
//        }
//    }

    private fun PageAction.update(page: Int) = when (this) {
        PageAction.FIRST -> 1
        PageAction.NEXT -> page.inc()
        PageAction.PREV -> if (page > 0) page.dec() else page
    }
}