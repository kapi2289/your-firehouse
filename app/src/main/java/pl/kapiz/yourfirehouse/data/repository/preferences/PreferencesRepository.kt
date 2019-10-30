package pl.kapiz.yourfirehouse.data.repository.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val preferences: SharedPreferences
) {

    var email: String?
        get() = preferences.getString("email", null)
        set(value) = preferences.edit { putString("edit", value) }

    var passwordHash: String?
        get() = preferences.getString("passwordHash", null)
        set(value) = preferences.edit { putString("passwordHash", value) }

    var ouId: Long
        get() = preferences.getLong("ouId", -1)
        set(value) = preferences.edit { putLong("ouId", value) }
}
