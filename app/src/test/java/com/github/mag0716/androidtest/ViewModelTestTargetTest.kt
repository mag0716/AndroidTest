package com.github.mag0716.androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    fun loadData() = runBlocking {
        val viewModelTestTarget = ViewModelTestTarget()
        viewModelTestTarget.data.observeForever(observer)

        // join で完了を待っている
        viewModelTestTarget.loadData(1).join()

        verify(observer).onChanged("Data1")
    }
}