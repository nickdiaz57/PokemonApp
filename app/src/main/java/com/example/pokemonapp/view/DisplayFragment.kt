package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemonapp.adapter.CardAdapter
import com.example.pokemonapp.databinding.FragmentDisplayBinding
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : Fragment() {
//home fragment
    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val cardAdapter by lazy { CardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDisplayBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokeViewModel.fetchCards()
        setupObserver()
    }

    fun initView() = with(binding) {

    }

    private fun setupObserver() = with(pokeViewModel) {
        cardList.observe(viewLifecycleOwner) {
            loadCards(it)
        }
    }

    private fun loadCards(cards: List<Card>) = with(binding.rvList) {
        adapter = cardAdapter
        cardAdapter.updateList(cards)
    }

//    private fun setupObservers() = with(katViewModel) {
//        katState.observe(viewLifecycleOwner) { state ->
//            binding.pbLoading.isVisible = state is ApiState.Loading
//            if (state is ApiState.Success) loadKats(state.data)
//            if (state is ApiState.Failure) handleFailure(state.errorMsg)
//        }
//
//        breedState.observe(viewLifecycleOwner) { state ->
//            binding.pbLoading.isVisible = state is ApiState.Loading
//            if (state is ApiState.Success) loadBreeds(state.data)
//            if (state is ApiState.Failure) handleFailure(state.errorMsg)
//        }
//    }
//
//    private fun loadKats(kats: List<Kat>) = with(binding.rvList) {
//        Log.d(TAG, "ApiState.Success: $kats")
//        if (adapter == null || adapter == breedAdapter) adapter = katAdapter
//        if (katViewModel.currentPageAction == PageAction.FIRST) katAdapter.clear()
//        breedAdapter.clear()
//        katAdapter.updateList(kats)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}