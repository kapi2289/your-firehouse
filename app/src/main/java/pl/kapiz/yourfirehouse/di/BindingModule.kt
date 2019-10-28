package pl.kapiz.yourfirehouse.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kapiz.yourfirehouse.di.scopes.PerActivity
import pl.kapiz.yourfirehouse.ui.main.MainActivity
import pl.kapiz.yourfirehouse.ui.main.MainModule

@Module
internal abstract class BindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}
