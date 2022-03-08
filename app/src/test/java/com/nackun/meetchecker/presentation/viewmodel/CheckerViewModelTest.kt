package com.nackun.meetchecker.presentation.viewmodel

import com.nackun.meetchecker.data.TestTimeDataSource
import com.nackun.meetchecker.data.time.TimeDataSource
import com.nackun.meetchecker.domain.usecase.AddCheckerUseCase
import com.nackun.meetchecker.domain.usecase.GetTodayCheckerUseCase
import com.nackun.meetchecker.domain.usecase.ListenTomorrowUseCase
import com.nackun.meetchecker.domain.util.toSimpleString
import com.nackun.meetchecker.log
import com.nackun.meetchecker.presentation.checker.CheckerState
import com.nackun.meetchecker.presentation.checker.CheckerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.component.get
import org.koin.core.component.inject
import java.time.LocalDateTime

/**
 * [CheckerViewModel]을 테스트하기 위한 Unit Test Class
 *
 * 1. CheckerViewModel init 후 state 값 확인
 *    `Check State After CheckerViewModel init`
 *
 * 2. 날짜가 변경되었을 경우 state 확인
 *    `Check State After Day Changed`
 *
 * 3. 클릭 시 state 확인
 *    `Check State After Clicked`
 */
@ExperimentalCoroutinesApi
internal class CheckerViewModelTest : ViewModelTest() {

    private lateinit var viewModel: CheckerViewModel

    private val addCheckerUseCase: AddCheckerUseCase by inject()
    private val getTodayCheckerUseCase: GetTodayCheckerUseCase by inject()
    private val listenTomorrowUseCase: ListenTomorrowUseCase by inject()

    override fun onBefore() {
        viewModel = CheckerViewModel(
            CheckerState(),
            addCheckerUseCase,
            getTodayCheckerUseCase,
            listenTomorrowUseCase,
        )
    }

    @Test
    fun `Check State After CheckerViewModel init`() {
        runBlockingTest {
            // given
            val state = viewModel.awaitState()
            state.log("state")

            // when
            val todayChecker = getTodayCheckerUseCase()
            todayChecker.log("todayChecker")

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
            ((get<TimeDataSource>()) as TestTimeDataSource).setTomorrow()
            val state = viewModel.awaitState()
            state.log("state")
            val tomorrow = LocalDateTime.now().plusDays(1).toSimpleString()
            tomorrow.log("tomorrow")

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
            beforeState.log("beforeState")

            // when
            viewModel.clickChecker(beforeState.today, beforeState.check)
            val afterState = viewModel.awaitState()
            afterState.log("afterState")

            // then
            // updateStatus(dateString, !check)
            assert(afterState.check == !beforeState.check)
            // addCheckerUseCase(!check)
            assert(afterState.today == beforeState.today)
        }
    }
}
