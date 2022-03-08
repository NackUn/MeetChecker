package com.nackun.meetchecker

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.nackun.meetchecker.data.di.dataSourceModule
import com.nackun.meetchecker.data.di.databaseModule
import com.nackun.meetchecker.data.di.repositoryModule
import com.nackun.meetchecker.di.androidModule
import com.nackun.meetchecker.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MeetCheckApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initFlipper()
        initMavericks()
        initKoin()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.start()
        }
    }

    private fun initMavericks() {
        Mavericks.initialize(this)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MeetCheckApplication)
            modules(
                listOf(
                    androidModule,
                    databaseModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                )
            )
        }
    }
}
