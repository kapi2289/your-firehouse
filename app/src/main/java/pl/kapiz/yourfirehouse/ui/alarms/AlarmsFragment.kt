package pl.kapiz.yourfirehouse.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import kotlinx.android.synthetic.main.fragment_alarms.*
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BaseFragment

class AlarmsFragment : BaseFragment(), AlarmsView {

    private val presenter = AlarmsPresenter()

    private val alarmsAdapter = FlexibleAdapter<AbstractFlexibleItem<*>>(null, null, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alarms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onAttachView(this)
    }

    override fun initView() {
        alarmsRecycler.apply {
            layoutManager = SmoothScrollLinearLayoutManager(context)
            adapter = alarmsAdapter
        }
    }

    override fun updateData(data: List<AlarmItem>) {
        alarmsAdapter.updateDataSet(data)
    }
}
