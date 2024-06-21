package com.danp.artexploreapp.paiting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp.artexploreapp.paiting.data.RetrofitInstance
import com.danp.artexploreapp.paiting.domain.Painting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PaintingsViewModel : ViewModel() {
    private val _agentsData = MutableStateFlow<List<Painting>>(emptyList())

    val agentsData: StateFlow<List<Painting>> = _agentsData

    init {
        viewModelScope.launch {
            try {
                val agents = RetrofitInstance.apiService.getPaintings()
                _agentsData.value = agents
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
