package pl.kapiz.yourfirehouse.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> {

    val disposable = CompositeDisposable()

    var view: T? = null

    open fun onAttachView(view: T, context: Context?) {
        this.view = view
    }

    open fun onDetachView() {
        view = null
        disposable.clear()
    }
}
