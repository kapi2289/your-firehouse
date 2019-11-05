package pl.kapiz.yourfirehouse.ui.map

import pl.kapiz.yourfirehouse.base.BaseView

interface MapView : BaseView {

    fun initView()

    fun setPosition(latitude: Double, longitude: Double, zoom: Double?)
}
