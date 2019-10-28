package pl.kapiz.yourfirehouse.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import kotlinx.android.synthetic.main.fragment_alarms.*
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BaseFragment
import javax.inject.Inject

class AlarmsFragment : BaseFragment(), AlarmsView {

    @Inject
    lateinit var presenter: AlarmsPresenter

    @Inject
    lateinit var alarmsAdapter: FlexibleAdapter<AbstractFlexibleItem<*>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onAttachView(this, context)
    }

    override fun initView() {
        alarmsRecycler.apply {
            layoutManager = SmoothScrollLinearLayoutManager(context)
            adapter = alarmsAdapter
        }

        alarmsSwipeRefresh.setOnRefreshListener { presenter.onSwipeRefresh() }
    }

    override fun updateData(data: List<AlarmItem>) {
        alarmsAdapter.updateDataSet(data)
    }

    override fun hideRefreshing() {
        alarmsSwipeRefresh.isRefreshing = false
    }

    override fun showProgress(show: Boolean) {
        alarmsProgress.visibility = if (show) VISIBLE else GONE
    }
}
