package pl.kapiz.yourfirehouse.ui.splash

import android.content.Context
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.repository.login.LoginRepository
import pl.kapiz.yourfirehouse.utils.SchedulersProvider
import timber.log.Timber
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val loginRepository: LoginRepository,
    private val schedulers: SchedulersProvider
) : BasePresenter<SplashView>() {

    override fun onAttachView(view: SplashView, context: Context?) {
        super.onAttachView(view, context)

        disposable.add(loginRepository.isLoggedIn()
            .subscribeOn(schedulers.backgroundThread)
            .observeOn(schedulers.mainThread)
            .subscribe({
                view.run {
                    if (it) openMainActivity()
                    else openLoginActivity()
                }
            }) {
                Timber.e(it)
            })
    }
}
