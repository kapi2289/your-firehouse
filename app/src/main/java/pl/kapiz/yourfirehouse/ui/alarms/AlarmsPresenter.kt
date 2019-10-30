package pl.kapiz.yourfirehouse.ui.alarms

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.repository.alarm.AlarmRepository
import timber.log.Timber
import javax.inject.Inject

class AlarmsPresenter @Inject constructor(
    private val alarmRepository: AlarmRepository
) : BasePresenter<AlarmsView>() {

    override fun onAttachView(view: AlarmsView, context: Context?) {
        super.onAttachView(view, context)
        view.run {
            initView()
            showProgress(true)
        }
        loadData()
    }

    private fun loadData(forceRefresh: Boolean = false) {
        Timber.i("Loading alarms...")

        disposable.add(
            alarmRepository.getAlarms(forceRefresh)
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
                    Timber.e(it)
                }
        )
    }

    fun onSwipeRefresh() {
        loadData(true)
    }
}
