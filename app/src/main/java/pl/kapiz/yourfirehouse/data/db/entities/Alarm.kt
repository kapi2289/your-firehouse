package pl.kapiz.yourfirehouse.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "alarms")
data class Alarm(

    @PrimaryKey
    val id: Int,

    val description: String,

    val acquired: LocalDateTime,

    val expired: LocalDateTime
)
