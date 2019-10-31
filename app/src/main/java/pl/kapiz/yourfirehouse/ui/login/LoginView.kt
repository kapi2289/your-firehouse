package pl.kapiz.yourfirehouse.ui.login

import pl.kapiz.yourfirehouse.base.BaseView

interface LoginView : BaseView {

    fun initView()

    fun showProgress(show: Boolean)

    fun showLoginForm(show: Boolean)

    fun openMainActivity()
}
