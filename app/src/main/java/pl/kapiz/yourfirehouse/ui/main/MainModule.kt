package pl.kapiz.yourfirehouse.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kapiz.yourfirehouse.di.scopes.PerFragment
import pl.kapiz.yourfirehouse.ui.alarms.AlarmsFragment
import pl.kapiz.yourfirehouse.ui.home.HomeFragment

@Module
abstract class MainModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindAlarmsFragment(): AlarmsFragment
}
