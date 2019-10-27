package pl.kapiz.yourfirehouse.ui.alarms

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.kapiz.yourfirehouse.App
import pl.kapiz.yourfirehouse.base.BasePresenter

class AlarmsPresenter : BasePresenter<AlarmsView>() {
    private val repository by lazy {
        (context?.applicationContext as App).alarmRepository
    }

    override fun onAttachView(view: AlarmsView, context: Context?) {
        super.onAttachView(view, context)
        view.run {
            initView()
            showProgress(true)
        }
        loadData()
    }

    private fun loadData(forceRefresh: Boolean = false) {
        disposable.add(
            repository.getAlarms(forceRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    view?.run {
                        showProgress(false)
                        hideRefreshing()
                    }
                }
                .subscribe({
                    view?.run {
                        updateData(it.map {
                            AlarmItem(it)
                        })
                    }
                }) {

                }
        )
    }

    fun onSwipeRefresh() {
        loadData(true)
    }
}
