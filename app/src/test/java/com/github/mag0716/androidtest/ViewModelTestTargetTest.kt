package com.github.mag0716.androidtest

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class)
class ViewModelTestTargetTest : CoroutineTest() {

    @Mock
    lateinit var observer: Observer<String>

    @Test
    fun loadData() = testDispatcher.runBlockingTest {
        val viewModelTestTarget = ViewModelTestTarget(testDispatcher)
        viewModelTestTarget.data.observeForever(observer)

        viewModelTestTarget.loadData(1)
        // delay されている場合は advanceTimeBy で時間を進める
        testDispatcher.advanceTimeBy(4000)

        verify(observer).onChanged("Data1")
    }
}