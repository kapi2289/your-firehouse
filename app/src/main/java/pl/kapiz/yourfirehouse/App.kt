package pl.kapiz.yourfirehouse

import pl.kapiz.yourfirehouse.data.db.AppDatabase
import pl.kapiz.yourfirehouse.data.repositories.alarm.AlarmRepository

class App : androidx.multidex.MultiDexApplication() {

    lateinit var db: AppDatabase

    lateinit var alarmRepository: AlarmRepository

    override fun onCreate() {
        super.onCreate()

        db = AppDatabase.newInstance(this)

        /**
         * TEMPORARY
         * TODO: Make API
         */
        alarmRepository = AlarmRepository(this)
    }
}
