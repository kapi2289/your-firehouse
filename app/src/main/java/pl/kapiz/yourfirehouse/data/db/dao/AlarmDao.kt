package pl.kapiz.yourfirehouse.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Maybe
import pl.kapiz.yourfirehouse.data.db.entities.Alarm
import javax.inject.Singleton

@Singleton
@Dao
interface AlarmDao {

    @Insert
    fun addAll(alarms: List<Alarm>)

    @Query("SELECT * FROM alarms")
    fun loadAll(): Maybe<List<Alarm>>

    @Query("SELECT * FROM alarms WHERE id = :id")
    fun load(id: Long): Maybe<Alarm>

    @Delete
    fun deleteAll(alarms: List<Alarm>)
}
