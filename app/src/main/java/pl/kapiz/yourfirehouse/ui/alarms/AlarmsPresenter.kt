package pl.kapiz.yourfirehouse.ui.alarms

import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.db.entities.Alarm

class AlarmsPresenter : BasePresenter<AlarmsView>() {

    override fun onAttachView(view: AlarmsView) {
        super.onAttachView(view)
        view.initView()
        loadData()
    }

    private fun loadData() {
        /**
         * TEST DATA
         */
        view?.run {
            updateData(
                listOf(
                    AlarmItem(Alarm(0, "Pożar lasu")),
                    AlarmItem(Alarm(1, "Kot na drzewie")),
                    AlarmItem(Alarm(2, "Płonący konar"))
                )
            )
        }
    }
}
