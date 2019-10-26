package com.github.mag0716.androidtest

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CoroutinesTestTarget(
    private val coroutinesDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadData(id: Int): String = withContext(coroutinesDispatcher) {
        delay(3000)
        "Data$id"
    }
}