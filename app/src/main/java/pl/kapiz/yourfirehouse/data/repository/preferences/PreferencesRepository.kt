package pl.kapiz.yourfirehouse.data.repository.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val preferences: SharedPreferences
) {

    var email: String?
        get() = preferences.getString("email", null)
        set(value) = preferences.edit { putString("email", value) }

    var secret: String?
        get() = preferences.getString("secret", null)
        set(value) = preferences.edit { putString("secret", value) }

    var userName: String?
        get() = preferences.getString("userName", null)
        set(value) = preferences.edit { putString("userName", value) }

    var ouName: String?
        get() = preferences.getString("ouName", null)
        set(value) = preferences.edit { putString("ouName", value) }

    var ouId: Long
        get() = preferences.getLong("ouId", -1)
        set(value) = preferences.edit { putLong("ouId", value) }
}
