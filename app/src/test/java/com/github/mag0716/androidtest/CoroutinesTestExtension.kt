package com.github.mag0716.androidtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext

// https://gist.github.com/RBusarow/70256d782e2d789cfa167a0163b2e22e
@ExperimentalCoroutinesApi
@ExtendWith(CoroutinesTestExtension::class)
abstract class CoroutineTest {
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
}

@ExperimentalCoroutinesApi
class CoroutinesTestExtension(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}