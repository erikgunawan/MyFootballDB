package id.ergun.myfootballdb.bases.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.bases.ui.match.BaseMatchAdapterUI
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.utils.gone
import id.ergun.myfootballdb.utils.toLocalDate
import id.ergun.myfootballdb.utils.toLocalTime
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext

class BaseMatchAdapter (private val events : List<Event>,
                        private val listener: (ItemClickListener),
                        private val calendarListener: AddToCalendarClickListener? = null,
                        private var addToCalendar: Boolean = false)
    : RecyclerView.Adapter<BaseMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(BaseMatchAdapterUI().createView(AnkoContext.create(parent.context, parent)))
        view.itemView.setOnClickListener {
            listener.onItemClick(it, view.adapterPosition)
        }
        view.ivAddToCalendar.setOnClickListener {
            calendarListener?.onAddToCalendarClick(it, view.adapterPosition)
        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position], addToCalendar)
    }

    override fun getItemCount(): Int = events.size

    fun getData(): List<Event> = events

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvDateEvent: TextView = itemView.findViewById(R.id.tv_date_event)
        private var tvTimeEvent: TextView = itemView.findViewById(R.id.tv_time_event)
        private var tvHomeTeam: TextView = itemView.findViewById(R.id.tv_home_team)
        private var tvHomeScore: TextView = itemView.findViewById(R.id.tv_home_score)
        private var tvAwayTeam: TextView = itemView.findViewById(R.id.tv_away_team)
        private var tvAwayScore: TextView = itemView.findViewById(R.id.tv_away_score)

        var ivAddToCalendar: ImageView = itemView.findViewById(R.id.iv_add_to_calendar)

        fun bindItem(event: Event, addToCalendar: Boolean) {
            tvDateEvent.text = toLocalDate(event.dateEvent)
            tvTimeEvent.text = toLocalTime(event.strTime)
            tvHomeTeam.text = event.strHomeTeam
            val homeScore = event.intHomeScore ?: ""
            tvHomeScore.text = homeScore.toString()
            tvAwayTeam.text = event.strAwayTeam

            val awayScore = event.intAwayScore ?: ""
            tvAwayScore.text = awayScore.toString()

            if (addToCalendar)
                ivAddToCalendar.visible()
            else
                ivAddToCalendar.gone()
        }
    }

    interface ItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface AddToCalendarClickListener {
        fun onAddToCalendarClick(v: View, position: Int)
    }
}