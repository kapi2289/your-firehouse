package pl.kapiz.yourfirehouse.ui.main

import android.content.Context
import pl.kapiz.yourfirehouse.base.BasePresenter

class MainPresenter : BasePresenter<MainView>() {

    override fun onAttachView(view: MainView, context: Context?) {
        super.onAttachView(view, context)
        view.initView()
    }
}
