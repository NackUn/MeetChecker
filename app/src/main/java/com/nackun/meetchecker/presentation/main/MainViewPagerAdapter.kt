package com.nackun.meetchecker.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nackun.meetchecker.presentation.calendar.CalendarFragment
import com.nackun.meetchecker.presentation.checker.CheckerFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = PAGE_COUNT

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> CheckerFragment()
            1 -> CalendarFragment()
            else -> CheckerFragment()
        }

    companion object {
        private const val PAGE_COUNT = 2
    }
}