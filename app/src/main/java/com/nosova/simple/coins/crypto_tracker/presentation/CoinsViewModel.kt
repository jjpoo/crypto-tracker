package com.nosova.simple.coins.observer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nosova.simple.coins.crypto_tracker.domain.model.Coin
import com.nosova.simple.coins.crypto_tracker.domain.repository.CoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    repository: CoinsRepository
) : ViewModel() {

    val coinsFlow: Flow<PagingData<Coin>> = repository.getCoins().cachedIn(viewModelScope)
}