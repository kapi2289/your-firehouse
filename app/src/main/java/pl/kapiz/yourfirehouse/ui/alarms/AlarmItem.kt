package pl.kapiz.yourfirehouse.ui.alarms

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.extensions.LayoutContainer
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.data.db.entity.Alarm

class AlarmItem(val alarm: Alarm) : AbstractFlexibleItem<AlarmItem.ViewHolder>() {
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.run {
            alarmItemDescription.text = alarm.description
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        var result = 1

        result = 14 * result + alarm.id
        result = 14 * result + alarm.description.hashCode()

        return result
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun getLayoutRes(): Int = R.layout.item_alarm

    class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter),
        LayoutContainer {

        val alarmItemDescription: TextView = view.findViewById(R.id.alarmItemDescription)

        override val containerView: View?
            get() = contentView
    }
}
