package pl.kapiz.yourfirehouse

import android.content.Context
import androidx.multidex.MultiDex
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.kapiz.yourfirehouse.di.DaggerAppComponent
import timber.log.Timber

class App : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
    }
}
