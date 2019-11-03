package pl.kapiz.yourfirehouse.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import pl.kapiz.yourfirehouse.App
import pl.kapiz.yourfirehouse.R
import java.util.concurrent.TimeUnit

val LocalDate.stringY_m_d: String
    get() = format(DateTimeFormatter.ISO_LOCAL_DATE)

val LocalDateTime.stringY_m_d: String
    get() = format(DateTimeFormatter.ISO_LOCAL_DATE)

val LocalDate.asMilliseconds
    get() = atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

val LocalDateTime.asMilliseconds
    get() = toInstant(ZoneOffset.UTC).toEpochMilli()

fun LocalDateTime.diffForHumans(other: LocalDateTime): String? {
    val diff = asMilliseconds - other.asMilliseconds

    val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    val months = days / 30
    val years = days / 365

    App.getContext()?.resources?.let { res ->
        return when {
            years > 0 -> res.getQuantityString(R.plurals.years_ago, years.toInt(), years.toInt())
            months > 0 -> res.getQuantityString(
                R.plurals.months_ago,
                months.toInt(),
                months.toInt()
            )
            days > 0 -> res.getQuantityString(R.plurals.days_ago, days.toInt(), days.toInt())
            hours > 0 -> res.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours.toInt())
            minutes > 0 -> res.getQuantityString(
                R.plurals.minutes_ago,
                minutes.toInt(),
                minutes.toInt()
            )
            seconds > 0 -> res.getQuantityString(
                R.plurals.seconds_ago,
                seconds.toInt(),
                seconds.toInt()
            )
            else -> ""
        }
    }

    return ""
}
