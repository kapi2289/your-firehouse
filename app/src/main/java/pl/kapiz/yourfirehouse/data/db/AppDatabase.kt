package pl.kapiz.yourfirehouse.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.JournalMode.TRUNCATE
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import pl.kapiz.yourfirehouse.data.db.dao.AlarmDao
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import pl.kapiz.yourfirehouse.data.db.migrations.Migration1_2

@Database(
    entities = [
        Alarm::class
    ],
    version = AppDatabase.VERSION_SCHEMA,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION_SCHEMA = 2

        private fun getMigrations(): Array<Migration> {
            return arrayOf(
                Migration1_2()
            )
        }

        fun newInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "your_firehouse_database")
                .setJournalMode(TRUNCATE)
                .fallbackToDestructiveMigrationFrom(VERSION_SCHEMA + 1)
                .fallbackToDestructiveMigrationOnDowngrade()
                .addMigrations(*getMigrations())
                .build()
        }
    }

    abstract fun alarmDao(): AlarmDao
}
