package first.android.cis.app

import android.app.Application
import first.android.cis.BuildConfig
import first.android.cis.di.appModule
import first.android.cis.di.dataModule
import first.android.cis.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}