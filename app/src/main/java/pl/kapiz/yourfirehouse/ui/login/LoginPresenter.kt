package pl.kapiz.yourfirehouse.ui.login

import android.content.Context
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.api.ApiService
import pl.kapiz.yourfirehouse.data.repository.login.LoginRepository
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import pl.kapiz.yourfirehouse.utils.SchedulersProvider
import timber.log.Timber
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val api: ApiService,
    private val preferences: PreferencesRepository,
    private val loginRepository: LoginRepository,
    private val schedulers: SchedulersProvider
) : BasePresenter<LoginView>() {

    override fun onAttachView(view: LoginView, context: Context?) {
        super.onAttachView(view, context)
        view.apply {
            initView()
            showProgress(false)
        }
    }

    private fun login(email: String, password: String) {
        preferences.apply {
            this.email = email
            this.secret = password.toSecret()
        }

        disposable.add(api.getUser()
            .map { loginRepository.saveUser(it) }
            .subscribeOn(schedulers.backgroundThread)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                view?.apply {
                    showLoginForm(false)
                    showProgress(true)
                }
            }
            .subscribe({
                view?.openMainActivity()
            }) {
                view?.apply {
                    showLoginForm(true)
                    showProgress(false)
                }
                Timber.e(it)
            })
    }

    fun onSubmitButtonClicked(email: String, password: String) {
        login(email, password)
    }
}
