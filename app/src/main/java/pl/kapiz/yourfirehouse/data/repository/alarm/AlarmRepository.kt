package pl.kapiz.yourfirehouse.data.repository.alarm

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Single
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepository @Inject constructor(
    private val local: AlarmRepositoryLocal,
    private val remote: AlarmRepositoryRemote
) {

    fun getAlarms(forceRefresh: Boolean = false): Single<List<Alarm>> {
        return local.getAlarms().filter { !forceRefresh }
            .switchIfEmpty(ReactiveNetwork.checkInternetConnectivity()
                .flatMap {
                    if (it) remote.getAlarms()
                    else Single.error(UnknownHostException())
                }.flatMap { new ->
                    local.getAlarms().toSingle(emptyList())
                        .doOnSuccess { old ->
                            local.deleteAlarms(old - new)
                            local.saveAlarms(new - old)
                        }
                }.flatMap { local.getAlarms().toSingle(emptyList()) })
    }
}
