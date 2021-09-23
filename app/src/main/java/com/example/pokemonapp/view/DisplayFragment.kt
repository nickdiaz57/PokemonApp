package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemonapp.databinding.FragmentDisplayBinding
import com.example.pokemonapp.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : Fragment() {
//home fragment
    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()

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
            binding.tvDisplay.text = it[0].name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}