package dev.mizoguche.kiqueue

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KiqueueApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KiqueueApplication)
            modules(kiqueueModule)
        }
    }
}