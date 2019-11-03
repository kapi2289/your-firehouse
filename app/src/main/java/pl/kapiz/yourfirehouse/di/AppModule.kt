package pl.kapiz.yourfirehouse.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import pl.kapiz.yourfirehouse.App
import pl.kapiz.yourfirehouse.utils.SchedulersProvider
import javax.inject.Singleton

@Module
internal class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context = app

    @Singleton
    @Provides
    fun provideSchedulersProvider() = SchedulersProvider()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}
