package pl.kapiz.yourfirehouse.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE IF EXISTS alarms")

        database.execSQL(
            """CREATE TABLE alarms (
            id INTEGER NOT NULL PRIMARY KEY,
            description TEXT,
            acquired INTEGER NOT NULL,
            expired INTEGER NOT NULL
        )"""
        )
    }
}
