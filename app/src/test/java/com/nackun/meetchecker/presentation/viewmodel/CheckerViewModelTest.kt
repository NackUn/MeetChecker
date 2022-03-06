package com.nackun.meetchecker.presentation.viewmodel

import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.domain.util.toSimpleString
import com.nackun.meetchecker.presentation.checker.CheckerState
import com.nackun.meetchecker.presentation.checker.CheckerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.component.inject
import java.time.LocalDateTime

/**
 * [CheckerViewModel]을 테스트하기 위한 Unit Test Class
 *
 * 1. CheckerViewModel init 후 state 값 확인
 *    Check State After CheckerViewModel init
 *
 * 2. 날짜가 변경되었을 경우 state 확인
 *    Check State After Day Changed
 *
 * 3. 클릭 시 state 확인
 *    Check State After Clicked
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
    fun `Check State After Day Changed`() {
        runBlockingTest {
            // given

            // when
            // 날짜가 바뀌면
            // TODO 날짜가 바뀌면 State 가 변경되었는지 확인합니다.
            //  날짜를 체크하는 비즈니스 로직 리팩토링 필요
            //  ListenTomorrowUseCase()

            val state = viewModel.awaitState()
            val tomorrow = LocalDateTime.now().plusDays(1).toSimpleString()

            // then
            assert(state.check == false)
            assert(state.today == tomorrow)
        }
    }

    @Test
    fun `Check State After Clicked`() {
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
