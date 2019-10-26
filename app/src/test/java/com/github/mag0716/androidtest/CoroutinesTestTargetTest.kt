package com.github.mag0716.androidtest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CoroutinesTestTargetTest : CoroutineTest() {

    @Test
    fun loadTest() = testDispatcher.runBlockingTest {
        val target = CoroutinesTestTarget(testDispatcher)
        val data = target.loadData(1)
        testDispatcher.advanceTimeBy(4000)
        Assert.assertThat(data, `is`("Data1"))
    }
}