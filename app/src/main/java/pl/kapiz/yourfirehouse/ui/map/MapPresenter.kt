package pl.kapiz.yourfirehouse.ui.map

import android.content.Context
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import javax.inject.Inject

class MapPresenter @Inject constructor(
    private val preferences: PreferencesRepository
) : BasePresenter<MapView>() {

    override fun onAttachView(view: MapView, context: Context?) {
        super.onAttachView(view, context)
        view.apply {
            initView()
            setPosition(
                preferences.ouLatitude.toDouble(),
                preferences.ouLongitude.toDouble(),
                16.0
            )
        }
    }
}
