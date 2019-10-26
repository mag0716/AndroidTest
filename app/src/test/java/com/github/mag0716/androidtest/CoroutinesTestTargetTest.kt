package com.github.mag0716.androidtest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutinesTestTargetTest {

    // テスト対象内で suspend fun を使っている場合に必須
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun loadTest() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val target = CoroutinesTestTarget(coroutinesTestRule.testDispatcher)
        val data = target.loadData(1)
        coroutinesTestRule.testDispatcher.advanceTimeBy(4000)
        Assert.assertThat(data, `is`("Data1"))
    }
}