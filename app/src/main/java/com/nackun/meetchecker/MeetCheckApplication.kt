package com.nackun.meetchecker

import android.app.Application
import com.airbnb.mvrx.Mavericks

class MeetCheckApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)
    }
}