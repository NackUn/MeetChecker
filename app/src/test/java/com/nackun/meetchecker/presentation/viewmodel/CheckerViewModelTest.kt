package com.nackun.meetchecker.presentation.viewmodel

import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.presentation.checker.CheckerState
import com.nackun.meetchecker.presentation.checker.CheckerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.component.inject

/**
 * [CheckerViewModel]을 테스트하기 위한 Unit Test Class
 *
 * 1. CheckerViewModel init 후 state 값 확인
 *    Check State After CheckerViewModel init
 * 2. 날짜가 변경되었을 경우 state 확인
 *
 * 3. 클릭 시 state 확인
 *    Check State After Click
 */
@ExperimentalCoroutinesApi
internal class CheckerViewModelTest : ViewModelTest() {

    private lateinit var viewModel: CheckerViewModel

    override fun onBefore() {
        viewModel = CheckerViewModel(CheckerState())
    }

    private val getTodayCheckerUseCase: GetTodayCheckerUseCase by inject()

    @Test
    fun `Check State After CheckerViewModel init`() {
        runBlockingTest {
            // given
            val state = viewModel.awaitState()

            // when
            val todayChecker = getTodayCheckerUseCase()

            // then
            assert(state.check == todayChecker.check)
            assert(state.today == todayChecker.dateString)
        }
    }

    @Test
    fun `Check State After Click`() {
        runBlockingTest {
            // given
            val beforeState = viewModel.awaitState()

            // when
            viewModel.clickChecker(beforeState.today, beforeState.check)
            val afterState = viewModel.awaitState()

            // then
            // updateStatus(dateString, !check)
            assert(afterState.check == !beforeState.check)
            // addCheckerUseCase(!check)
            assert(afterState.today == beforeState.today)
        }
    }
}
