package pl.kapiz.yourfirehouse.data.repositories.alarm

import android.content.Context
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Single
import pl.kapiz.yourfirehouse.data.db.entities.Alarm
import java.net.UnknownHostException

class AlarmRepository(context: Context) {

    private val local = AlarmRepositoryLocal(context)
    private val remote = AlarmRepositoryRemote()

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
