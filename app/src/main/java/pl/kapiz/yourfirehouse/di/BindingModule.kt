package pl.kapiz.yourfirehouse.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kapiz.yourfirehouse.di.scopes.PerActivity
import pl.kapiz.yourfirehouse.di.scopes.PerFragment
import pl.kapiz.yourfirehouse.ui.alarms.AlarmsFragment
import pl.kapiz.yourfirehouse.ui.home.HomeFragment
import pl.kapiz.yourfirehouse.ui.login.LoginActivity
import pl.kapiz.yourfirehouse.ui.main.MainActivity
import pl.kapiz.yourfirehouse.ui.splash.SplashActivity

@Module
@Suppress("unused")
internal abstract class BindingModule {

    /**
     * ACTIVITIES
     */

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    /**
     * FRAGMENTS
     */

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindAlarmsFragment(): AlarmsFragment
}
