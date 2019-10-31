package pl.kapiz.yourfirehouse.base

import dagger.android.support.DaggerFragment

abstract class BaseFragment<T : BasePresenter<out BaseView>> : DaggerFragment(), BaseView {

    abstract var presenter: T

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }
}
