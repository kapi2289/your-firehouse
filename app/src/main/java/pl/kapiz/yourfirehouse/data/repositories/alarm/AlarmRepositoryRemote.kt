package pl.kapiz.yourfirehouse.data.repositories.alarm

import io.reactivex.Single
import org.threeten.bp.LocalDateTime
import pl.kapiz.yourfirehouse.data.db.entities.Alarm

class AlarmRepositoryRemote {

    fun getAlarms(): Single<List<Alarm>> {
        /**
         * TEST DATA
         */
        return Single.fromCallable {
            listOf(
                Alarm(1, "Pożar lasu", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5)),
                Alarm(2, "Nieużytki", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5)),
                Alarm(3, "Pożar komina", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5)),
                Alarm(4, "Kot na drzewie", LocalDateTime.now(), LocalDateTime.now().plusMinutes(5))
            )
        }
    }
}
