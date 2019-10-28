package pl.kapiz.yourfirehouse.data

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.kapiz.yourfirehouse.data.db.AppDatabase
import pl.kapiz.yourfirehouse.data.db.dao.AlarmDao
import javax.inject.Singleton

@Module
internal class RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.newInstance(context)

    @Singleton
    @Provides
    fun provideAlarmDao(database: AppDatabase): AlarmDao = database.alarmDao()
}
