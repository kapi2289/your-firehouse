package pl.kapiz.yourfirehouse.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_alarm_details.*
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.data.db.entity.Alarm

class AlarmDetailsDialog : DialogFragment() {

    private lateinit var alarm: Alarm

    companion object {
        private const val ARGUMENT_KEY = "Item"

        fun newInstance(alarm: Alarm) = AlarmDetailsDialog().apply {
            arguments = Bundle().apply { putSerializable(ARGUMENT_KEY, alarm) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_TITLE, 0)
        arguments?.run {
            alarm = getSerializable(ARGUMENT_KEY) as Alarm
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_alarm_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        alarmDialogDescription.text = alarm.description
    }
}
