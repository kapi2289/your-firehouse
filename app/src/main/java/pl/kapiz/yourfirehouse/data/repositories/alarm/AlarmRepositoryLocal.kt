package pl.kapiz.yourfirehouse.data.repositories.alarm

import io.reactivex.Maybe
import pl.kapiz.yourfirehouse.data.db.dao.AlarmDao
import pl.kapiz.yourfirehouse.data.db.entities.Alarm
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepositoryLocal @Inject constructor(private val alarmDb: AlarmDao) {

    fun getAlarms(): Maybe<List<Alarm>> {
        return alarmDb.loadAll()
            .filter { it.isNotEmpty() }
    }

    fun saveAlarms(alarms: List<Alarm>) {
        alarmDb.addAll(alarms)
    }

    fun deleteAlarms(alarms: List<Alarm>) {
        alarmDb.deleteAll(alarms)
    }
}
