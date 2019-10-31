package pl.kapiz.yourfirehouse.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kapiz.yourfirehouse.di.scopes.PerActivity
import pl.kapiz.yourfirehouse.ui.login.LoginActivity
import pl.kapiz.yourfirehouse.ui.main.MainActivity
import pl.kapiz.yourfirehouse.ui.main.MainModule
import pl.kapiz.yourfirehouse.ui.splash.SplashActivity

@Module
internal abstract class BindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity
}
