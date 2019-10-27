package pl.kapiz.yourfirehouse.ui.main

import pl.kapiz.yourfirehouse.base.BasePresenter

class MainPresenter : BasePresenter<MainView>() {

    override fun onAttachView(view: MainView) {
        super.onAttachView(view)
        view.initView()
    }
}
