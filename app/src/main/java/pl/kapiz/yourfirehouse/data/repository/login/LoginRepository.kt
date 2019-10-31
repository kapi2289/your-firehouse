package pl.kapiz.yourfirehouse.data.repository.login

import io.reactivex.Single
import pl.kapiz.yourfirehouse.data.api.pojo.User
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val preferences: PreferencesRepository
) {

    fun isLoggedIn(): Single<Boolean> {
        return Single.fromCallable {
            preferences.email != null && preferences.secret != null
        }
    }

    fun saveUser(user: User): User {
        return user.also {
            preferences.apply {
                ouId = it.ouId
                ouName = it.ouName
                userName = it.name
            }
        }
    }
}
