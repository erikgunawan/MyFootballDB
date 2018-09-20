package id.ergun.myfootballdb.modules.matchschedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.utils.toLocalDate
import org.jetbrains.anko.AnkoContext

class MatchScheduleAdapter (private val event_list : List<Event>,
                   private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<MatchScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(MatchScheduleAdapterUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event_list[position], listener)
    }

    override fun getItemCount(): Int = event_list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tvDateEvent: TextView
        private lateinit var tvHomeTeam: TextView
        private lateinit var tvHomeScore: TextView
        private lateinit var tvAwayTeam: TextView
        private lateinit var tvAwayScore: TextView

        fun bindItem(event_list: Event, listener: (Event) -> Unit) {

            tvDateEvent = itemView.findViewById(R.id.tv_date_event)
            tvHomeTeam = itemView.findViewById(R.id.tv_home_team)
            tvHomeScore = itemView.findViewById(R.id.tv_home_score)
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team)
            tvAwayScore = itemView.findViewById(R.id.tv_away_score)

            tvDateEvent.text = toLocalDate(event_list.dateEvent)
            tvHomeTeam.text = event_list.strHomeTeam
            val homescore = event_list.intHomeScore ?: ""
            tvHomeScore.text = homescore.toString()
            tvAwayTeam.text = event_list.strAwayTeam

            val awayscore = event_list.intAwayScore ?: ""
            tvAwayScore.text = awayscore.toString()

            itemView.setOnClickListener {
                listener(event_list)
            }
        }
    }
}