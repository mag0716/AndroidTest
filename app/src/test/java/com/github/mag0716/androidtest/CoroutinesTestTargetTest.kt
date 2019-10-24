package com.github.mag0716.androidtest

import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test

class CoroutinesTestTargetTest {
    @Test
    fun loadTest() = runBlocking {
        val target = CoroutinesTestTarget()
        val data = target.loadData(1)
        Assert.assertThat(data, `is`("Data1"))
    }
}