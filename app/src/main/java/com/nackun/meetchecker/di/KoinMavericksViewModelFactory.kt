package com.nackun.meetchecker.di

import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.nackun.meetchecker.presentation.base.BaseMavericksViewModel
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.error.NoBeanDefFoundException
import org.koin.core.error.NoScopeDefFoundException
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.get

/**
 * A [MvRxViewModelFactory] which makes it easy to use existing [Koin] container with [viewModel]
 * factory methods for ViewModel creation. This class should be implemented by the companion object
 * of every ViewModel which wants to use [Koin] bindings.
 *
 * This class accesses either [org.koin.core.scope.Scope] provided by [MvRxKoinScopeProvider] from
 * [ViewModelContext], or rootScope of [Koin], if no [MvRxKoinScopeProvider] was provided
 * (note, that prior to this, `startKoin` should be called).
 * Than the scope is used to get or create ViewModel for requested [viewModelClass].
 *
 * Here is an example, how you can attach this factory to your ViewModel:
 *
 * class MyViewModel constructor(...): BaseViewModel<MyState>(...) {
 *
 *   companion object : KoinMavericksViewModelFactory<MyViewModel, MyState>(MyViewModel::class.java)
 *
 * }
 *
 * @param viewModelClass The [Class] of the ViewModel being requested for creation
 */

abstract class KoinMavericksViewModelFactory<VM : BaseMavericksViewModel<S>, S : MavericksState>(
    private val viewModelClass: Class<out BaseMavericksViewModel<S>>
) : MavericksViewModelFactory<VM, S>, ViewModel() {

    @OptIn(KoinInternalApi::class)
    override fun create(viewModelContext: ViewModelContext, state: S): VM? {
        val koinScope = try {
            GlobalContext.get().scopeRegistry.rootScope
        } catch (e: IllegalStateException) {
            /**
             * If couldn't get [rootScope],
             * throw [KoinNoScopeFoundException] to show problem with setup.
             */
            throw KoinNoScopeFoundException(e)
        } catch (e: NoScopeDefFoundException) {
            /**
             * If couldn't get or create [org.koin.core.scope.Scope] from [ViewModelContext],
             * throw [KoinNoScopeFoundException] to show problem with setup.
             */
            throw KoinNoScopeFoundException(e)
        }

        return try {
            koinScope.get(clazz = viewModelClass) {
                parametersOf(state)
            }
        } catch (e: NoBeanDefFoundException) {
            /**
             * If no factory method was found in [koinScope] for given [viewModelClass],
             * throw [KoinNoFactoryFoundException] to show problem with setup.
             */
            throw KoinNoFactoryFoundException(e)
        }
    }
}

class KoinNoScopeFoundException(cause: Throwable? = null) : Exception(cause)
class KoinNoFactoryFoundException(cause: Throwable? = null) : Exception(cause)
