package pl.kapiz.yourfirehouse.data.repository.alarm

import io.reactivex.Single
import pl.kapiz.yourfirehouse.data.api.ApiService
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepositoryRemote @Inject constructor(
    private val api: ApiService,
    private val preferences: PreferencesRepository
) {

    fun getAlarms(count: Int = 10, offset: Int = 0): Single<List<Alarm>> {
        return api.getAlarms(preferences.ouId, count, offset)
    }
}
