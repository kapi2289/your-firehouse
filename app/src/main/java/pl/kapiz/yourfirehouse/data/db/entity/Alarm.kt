package pl.kapiz.yourfirehouse.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

@Entity(tableName = "alarms")
data class Alarm(

    @PrimaryKey
    val id: Int,

    val description: String,

    @SerializedName("aquired") // Typo in REST API
    val acquired: LocalDateTime,

    @SerializedName("expiration")
    val expired: LocalDateTime
)
