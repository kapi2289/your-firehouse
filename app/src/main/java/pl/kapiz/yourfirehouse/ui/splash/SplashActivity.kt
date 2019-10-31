package pl.kapiz.yourfirehouse.ui.splash

import android.os.Bundle
import pl.kapiz.yourfirehouse.base.BaseActivity
import pl.kapiz.yourfirehouse.ui.login.LoginActivity
import pl.kapiz.yourfirehouse.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {

    @Inject
    override lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.onAttachView(this, this)
    }

    override fun openMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this))
        finish()
    }
}
