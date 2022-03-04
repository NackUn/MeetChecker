package com.nackun.meetchecker.presentation.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.airbnb.mvrx.test.MvRxTestRule
import com.nackun.meetchecker.di.testAppModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

internal abstract class ViewModelTest : KoinTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mvrxRule = MvRxTestRule()

    @Mock
    private lateinit var context: Application

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        startKoin {
            androidContext(context)
            modules(testAppModule)
        }
        onBefore()
    }

    protected abstract fun onBefore()

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        stopKoin()
    }
}
