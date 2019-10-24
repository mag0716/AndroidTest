package com.github.mag0716.androidtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CoroutinesTestTarget {

    suspend fun loadData(id: Int): String = withContext(Dispatchers.IO) {
        delay(3000)
        "Data$id"
    }
}