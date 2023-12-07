package com.nosova.simple.coins.crypto_tracker.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nosova.simple.coins.crypto_tracker.R
import com.nosova.simple.coins.crypto_tracker.databinding.FragmentCoinDetailsBinding
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsFragment : Fragment(R.layout.fragment_coin_details) {

    private lateinit var binding: FragmentCoinDetailsBinding
    private val coinArgs: CoinDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        getDataFromArgs()
        return binding.root
    }

    private fun getDataFromArgs() {
        try {
            val data = coinArgs.coinArgs
            setUpScreen(data)
        } catch (e: Exception) {
            e.message
        }
    }

    private fun setUpScreen(data: Coin) {
        binding.apply {
            tvName.text = data.name
            tvDescription.text = data.price
            loadCoinImage(binding.ivCoin, data.iconUrl)
        }
    }

    private fun loadCoinImage(ivIcon: ImageView, url: String) {
        val context = ivIcon.context
        if (url.isNotBlank()) {
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_money)
                .error(R.drawable.ic_money)
                .into(ivIcon)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_money)
                .into(ivIcon)
        }
    }
}