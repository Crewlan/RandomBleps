package com.wolfcodea.randombleps.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfcodea.randombleps.core.exceptions.DataOrException
import com.wolfcodea.randombleps.data.datasource.AnimalsRandomRemoteDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalsRandomViewModel @Inject constructor(
    private val datasource: AnimalsRandomRemoteDatasource) : ViewModel() {
    val data: MutableState<DataOrException<AnimalsRandom, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )


     fun getAnimalsRandom(animalName: String) {
        viewModelScope.launch {
            data.value.loading = true
            data.value = datasource.getAnimalsRandom(animalName)
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }
}