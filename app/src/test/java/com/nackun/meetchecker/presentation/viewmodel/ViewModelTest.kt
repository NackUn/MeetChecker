package com.nackun.meetchecker.presentation.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nackun.meetchecker.MainCoroutineScopeRule
import com.nackun.meetchecker.presentation.di.testAppModule
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

    @Mock
    private lateinit var context: Application

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        startKoin {
            androidContext(context)
            modules(testAppModule)
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        stopKoin()
    }

//    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T> {
//        val testObserver = LiveDataTestObserver<T>()
//        observeForever(testObserver)
//        return testObserver
//    }
}
