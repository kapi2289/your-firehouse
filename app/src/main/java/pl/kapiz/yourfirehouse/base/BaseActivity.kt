package pl.kapiz.yourfirehouse.base

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : BasePresenter<out BaseView>> : DaggerAppCompatActivity(), BaseView {

    abstract var presenter: T

    override fun onDestroy() {
        super.onDestroy()
        invalidateOptionsMenu()
        presenter.onDetachView()
    }
}
