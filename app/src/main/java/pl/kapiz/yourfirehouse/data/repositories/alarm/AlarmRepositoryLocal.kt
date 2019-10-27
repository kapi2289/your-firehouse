package pl.kapiz.yourfirehouse.data.repositories.alarm

import android.content.Context
import io.reactivex.Maybe
import pl.kapiz.yourfirehouse.App
import pl.kapiz.yourfirehouse.data.db.entities.Alarm

class AlarmRepositoryLocal(private val context: Context) {
    private val database by lazy {
        (context.applicationContext as App).db.alarmDao
    }

    fun getAlarms(): Maybe<List<Alarm>> {
        return database.loadAll()
            .filter { it.isNotEmpty() }
    }

    fun saveAlarms(alarms: List<Alarm>) {
        database.addAll(alarms)
    }

    fun deleteAlarms(alarms: List<Alarm>) {
        database.deleteAll(alarms)
    }
}
