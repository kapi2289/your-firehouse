package pl.kapiz.yourfirehouse.ui.alarms

import pl.kapiz.yourfirehouse.base.BaseView

interface AlarmsView : BaseView {

    fun initView()

    fun updateData(data: List<AlarmItem>)

    fun hideRefreshing()

    fun showProgress(show: Boolean)
}
