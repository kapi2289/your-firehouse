package pl.kapiz.yourfirehouse.ui.main

import android.content.Context
import android.view.MenuItem
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BasePresenter
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val preferences: PreferencesRepository
) : BasePresenter<MainView>() {

    override fun onAttachView(view: MainView, context: Context?) {
        super.onAttachView(view, context)
        view.initView()
    }

    private fun logout(): Boolean {
        preferences.apply {
            email = null
            secret = null
        }

        view?.openLoginActivity()

        return true
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> logout()
            else -> false
        }
    }
}
