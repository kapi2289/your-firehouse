package pl.kapiz.yourfirehouse.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.kapiz.yourfirehouse.App
import pl.kapiz.yourfirehouse.data.RepositoryModule
import pl.kapiz.yourfirehouse.data.api.ApiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BindingModule::class,
        RepositoryModule::class,
        ApiModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}
