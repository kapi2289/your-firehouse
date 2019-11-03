package pl.kapiz.yourfirehouse.ui.alarms

import pl.kapiz.yourfirehouse.base.BaseView
import pl.kapiz.yourfirehouse.data.db.entity.Alarm

interface AlarmsView : BaseView {

    fun initView()

    fun updateData(data: List<Alarm>)

    fun hideRefreshing()

    fun showProgress(show: Boolean)

    fun showAlarmDetailsDialog(alarm: Alarm)
}
