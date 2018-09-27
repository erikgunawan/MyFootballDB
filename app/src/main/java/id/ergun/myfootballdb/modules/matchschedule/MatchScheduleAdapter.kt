package id.ergun.myfootballdb.modules.matchschedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.utils.toLocalDate
import org.jetbrains.anko.AnkoContext

class MatchScheduleAdapter (private val event_list : List<Event>,
                   private val listener: (ItemClickListener))
    : RecyclerView.Adapter<MatchScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchScheduleAdapter.ViewHolder {
        val view = ViewHolder(MatchScheduleAdapterUI().createView(AnkoContext.create(parent.context, parent)))
        view.itemView.setOnClickListener {
            listener.onItemClick(it, view.adapterPosition)
        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event_list[position])
    }

    override fun getItemCount(): Int = event_list.size

    fun getData(): List<Event> = event_list

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tvDateEvent: TextView
        private lateinit var tvHomeTeam: TextView
        private lateinit var tvHomeScore: TextView
        private lateinit var tvAwayTeam: TextView
        private lateinit var tvAwayScore: TextView

        fun bindItem(event_list: Event) {

            tvDateEvent = itemView.findViewById(R.id.tv_date_event)
            tvHomeTeam = itemView.findViewById(R.id.tv_home_team)
            tvHomeScore = itemView.findViewById(R.id.tv_home_score)
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team)
            tvAwayScore = itemView.findViewById(R.id.tv_away_score)

            tvDateEvent.text = toLocalDate(event_list.dateEvent)
            tvHomeTeam.text = event_list.strHomeTeam
            val homeScore = event_list.intHomeScore ?: ""
            tvHomeScore.text = homeScore.toString()
            tvAwayTeam.text = event_list.strAwayTeam

            val awayScore = event_list.intAwayScore ?: ""
            tvAwayScore.text = awayScore.toString()
        }
    }

    interface ItemClickListener {
        fun onItemClick(v: View, position: Int)
    }
}