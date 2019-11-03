package pl.kapiz.yourfirehouse.ui.alarms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDateTime
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import pl.kapiz.yourfirehouse.utils.diffForHumans
import pl.kapiz.yourfirehouse.utils.stringY_m_d

class AlarmsAdapter(val alarms: List<Alarm>) : RecyclerView.Adapter<AlarmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alarm = alarms[position]

        holder.run {
            alarmItemDescription.text = alarm.description
            alarmItemDate.text = alarm.acquired.stringY_m_d
            alarmItemDate.text = LocalDateTime.now().diffForHumans(alarm.acquired)
        }
    }

    override fun getItemCount(): Int = alarms.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val alarmItemDescription: TextView = view.findViewById(R.id.alarmItemDescription)
        val alarmItemDate: TextView = view.findViewById(R.id.alarmItemDate)
    }
}
