package com.github.mag0716.androidtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelTestTarget : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    // viewModelScope で実行する処理内で suspend fun を使っている場合、完了を知る手段がないので Job を戻り値にする
    fun loadData(id: Int) = viewModelScope.launch {
        val data = CoroutinesTestTarget().loadData(id)
        _data.value = data
    }
}