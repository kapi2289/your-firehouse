package pl.kapiz.yourfirehouse.ui.alarms

import pl.kapiz.yourfirehouse.base.BasePresenter

class AlarmsPresenter : BasePresenter<AlarmsView>() {

    override fun onAttachView(view: AlarmsView) {
        super.onAttachView(view)
        view.initView()
    }
}
