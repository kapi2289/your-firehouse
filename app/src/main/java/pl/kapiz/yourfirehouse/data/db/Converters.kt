package pl.kapiz.yourfirehouse.data.db

import androidx.room.TypeConverter
import org.threeten.bp.*
import java.util.*

class Converters {

    @TypeConverter
    fun timestampToDate(value: Long?): LocalDate? = value?.let {
        DateTimeUtils.toInstant(Date(it)).atZone(ZoneOffset.UTC).toLocalDate()
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? = date?.run {
        atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    @TypeConverter
    fun timestampToDateTime(value: Long?): LocalDateTime? = value?.let {
        LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
    }

    @TypeConverter
    fun dateTimeToTimestamp(date: LocalDateTime?): Long? = date?.run {
        toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}
