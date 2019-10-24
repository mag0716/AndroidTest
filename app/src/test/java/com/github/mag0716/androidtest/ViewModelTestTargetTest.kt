package com.github.mag0716.androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
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

    @get:Rule
    val rule = MockitoJUnit.rule().strictness(Strictness.WARN)

    @Mock
    lateinit var observer: Observer<String>

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadData() = runBlocking {
        val viewModelTestTarget = ViewModelTestTarget()
        viewModelTestTarget.data.observeForever(observer)

        // join で完了を待っている
        // TODO: テストは成功するが、プロダクト側、テスト側にも対応が必要で絶対忘れる
        viewModelTestTarget.loadData(1).join()

        verify(observer).onChanged("Data1")
    }
}