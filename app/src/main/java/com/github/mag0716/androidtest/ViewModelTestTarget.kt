package com.github.mag0716.androidtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTestTarget(
    private val coroutinesDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun loadData(id: Int) {
        viewModelScope.launch {
            val data = CoroutinesTestTarget(coroutinesDispatcher).loadData(id)
            _data.value = data
        }
    }
}