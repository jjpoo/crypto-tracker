package com.nosova.simple.coins.crypto_tracker.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.nosova.simple.coins.crypto_tracker.R
import com.nosova.simple.coins.crypto_tracker.databinding.FragmentCoinsBinding
import com.nosova.simple.coins.observer.presentation.CoinsViewModel
import com.nosova.simple.coins.crypto_tracker.presentation.adapter.CoinRankingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinsFragment : Fragment(R.layout.fragment_coins) {

    private val viewModel: CoinsViewModel by viewModels()

    private lateinit var coinsAdapter: CoinRankingAdapter
    private lateinit var binding: FragmentCoinsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        loadRvData()
        return binding.root
    }

    private fun setupRecyclerView() {
        coinsAdapter = CoinRankingAdapter()

        binding.rvCoinList.apply {
            adapter = coinsAdapter
            layoutManager = LinearLayoutManager(requireActivity())

            coinsAdapter.addLoadStateListener { loadStates ->
                this.isVisible = loadStates.refresh is LoadState.NotLoading
                binding.propgressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    private fun loadRvData() {
        lifecycleScope.launch {
            viewModel.coinsFlow.collect {
                coinsAdapter.submitData(it)
            }
        }
    }
}