package com.github.mag0716.androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
class ViewModelTestTargetTest {

    // LiveData を使っている場合に必須
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // テスト対象内で suspend fun を使っている場合に必須
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val rule = MockitoJUnit.rule().strictness(Strictness.WARN)

    @Mock
    lateinit var observer: Observer<String>

    @Test
    fun loadData() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val viewModelTestTarget = ViewModelTestTarget(coroutinesTestRule.testDispatcher)
        viewModelTestTarget.data.observeForever(observer)

        viewModelTestTarget.loadData(1)
        // delay されている場合は advanceTimeBy で時間を進める
        coroutinesTestRule.testDispatcher.advanceTimeBy(4000)

        verify(observer).onChanged("Data1")
    }
}