package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pokemonapp.databinding.FragmentDetailsBinding
import com.example.pokemonapp.util.loadWithGlide

class DetailsFragment : Fragment() {
//more info about a particular card, option to favorite a card
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
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
        Log.e("Details Fragment", "$args")
        val card = args.selectedCard
        card.images?.large?.let { ivDetails.loadWithGlide(it) }
        tvDetailsInfo.text = card.name
    }
    //put the random card button in the action bar
    //if no image data or no card selected, display placeholder image

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}