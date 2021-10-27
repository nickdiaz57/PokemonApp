package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.pokemonapp.databinding.FragmentDetailsBinding
import com.example.pokemonapp.util.loadWithGlide
import com.example.pokemonapp.viewmodel.PokeViewModel

class DetailsFragment : Fragment() {
//more info about a particular card, option to favorite a card
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        val card = args.selectedCard
        (activity as AppCompatActivity).supportActionBar?.title = card.name
        card.images?.large?.let { ivDetails.loadWithGlide(it) }
        tvDetailsInfo.text = card.name

        btnFav.setOnClickListener{
            pokeViewModel.favoriteCard(card)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.title = "Pokemon App"
        _binding = null
    }
}