package com.nosova.simple.coins.crypto_tracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.nosova.simple.coins.crypto_tracker.R
import com.nosova.simple.coins.crypto_tracker.databinding.CoinItemBinding
import com.nosova.simple.coins.crypto_tracker.common.toPrice
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin
import com.nosova.simple.coins.crypto_tracker.presentation.screens.CoinsFragmentDirections

class CoinRankingAdapter : PagingDataAdapter<Coin, CoinsViewHolder>(ListComparator) {
    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin = getItem(position) ?: return
        with(holder.binding) {
            holder.itemView.tag = coin
            tvCoinName.text = coin.name
            tvCoinPrice.text = coin.price.toPrice(2)
            tvCoinSymbol.text = coin.symbol
            loadCoinImage(ivCoin, coin.iconUrl)

            holder.itemView.setOnClickListener {
                val action = CoinsFragmentDirections.actionCoinsFragmentToBlankFragment(coin)
                val navController = Navigation.findNavController(it)
                navController.navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CoinItemBinding.inflate(layoutInflater, parent, false)
        return CoinsViewHolder(binding)
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

    object ListComparator : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Coin,
            newItem: Coin
        ): Boolean {
            return oldItem == newItem
        }
    }
}


