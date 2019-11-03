package pl.kapiz.yourfirehouse.ui.alarms

import android.content.Context
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import pl.kapiz.yourfirehouse.data.repository.alarm.AlarmRepository
import pl.kapiz.yourfirehouse.utils.SchedulersProvider
import timber.log.Timber
import javax.inject.Inject

class AlarmsPresenter @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val schedulers: SchedulersProvider
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
                .subscribeOn(schedulers.backgroundThread)
                .observeOn(schedulers.mainThread)
                .doFinally {
                    view?.run {
                        showProgress(false)
                        hideRefreshing()
                    }
                }
                .subscribe({
                    view?.updateData(it)
                }) {
                    Timber.e(it)
                }
        )
    }

    fun onSwipeRefresh() {
        loadData(true)
    }

    fun onItemClickListener(alarm: Alarm) {
        view?.showAlarmDetailsDialog(alarm)
    }
}
