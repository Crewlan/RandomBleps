package com.wolfcodea.randombleps.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfcodea.randombleps.core.exceptions.DataOrException
import com.wolfcodea.randombleps.data.datasource.AnimalsTagRemoteDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalsTagViewModel @Inject constructor(private val datasource: AnimalsTagRemoteDatasource): ViewModel(){
    val data : MutableState<DataOrException<AnimalsTags, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    init {
        getAnimalsTag()
    }

    private fun getAnimalsTag(){
        viewModelScope.launch {
            data.value.loading = true
            data.value = datasource.getAnimalsTag()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}