package com.github.mag0716.androidtest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExperimentalCoroutinesApi
class CoroutinesTestTargetTest : CoroutineTest() {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 1])
    fun loadTest(id: Int) = testDispatcher.runBlockingTest {
        val target = CoroutinesTestTarget(testDispatcher)
        val data = target.loadData(id)
        testDispatcher.advanceTimeBy(4000)
        Assert.assertThat(data, `is`("Data$id"))
    }
}