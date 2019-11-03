package pl.kapiz.yourfirehouse.data.db

import androidx.room.TypeConverter
import org.threeten.bp.*
import pl.kapiz.yourfirehouse.utils.asMilliseconds
import java.util.*

class Converters {

    @TypeConverter
    fun timestampToDate(value: Long?): LocalDate? = value?.let {
        DateTimeUtils.toInstant(Date(it)).atZone(ZoneOffset.UTC).toLocalDate()
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? = date?.asMilliseconds

    @TypeConverter
    fun timestampToDateTime(value: Long?): LocalDateTime? = value?.let {
        LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
    }

    @TypeConverter
    fun dateTimeToTimestamp(date: LocalDateTime?): Long? = date?.asMilliseconds
}
