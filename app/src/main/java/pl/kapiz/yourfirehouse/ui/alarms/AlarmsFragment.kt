package pl.kapiz.yourfirehouse.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_alarms.*
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BaseFragment
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import javax.inject.Inject

class AlarmsFragment : BaseFragment<AlarmsPresenter>(), AlarmsView {

    @Inject
    override lateinit var presenter: AlarmsPresenter

    private lateinit var alarmsAdapter: RecyclerView.Adapter<*>

    private val alarms = mutableListOf<Alarm>()

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
        alarmsAdapter = AlarmsAdapter(alarms)

        alarmsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = alarmsAdapter
        }

        alarmsSwipeRefresh.setOnRefreshListener { presenter.onSwipeRefresh() }
    }

    override fun updateData(data: List<Alarm>) {
        alarms.apply {
            clear()
            addAll(data)
        }
        alarmsAdapter.notifyDataSetChanged()
    }

    override fun hideRefreshing() {
        alarmsSwipeRefresh.isRefreshing = false
    }

    override fun showProgress(show: Boolean) {
        alarmsProgress.visibility = if (show) VISIBLE else GONE
    }
}
