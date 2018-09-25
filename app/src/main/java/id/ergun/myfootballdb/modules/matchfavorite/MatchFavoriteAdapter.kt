package id.ergun.myfootballdb.modules.matchfavorite

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.db.MatchFavorite
import id.ergun.myfootballdb.modules.matchschedule.MatchScheduleAdapterUI
import id.ergun.myfootballdb.utils.toLocalDate
import org.jetbrains.anko.AnkoContext

class MatchFavoriteAdapter(private val event_list : List<MatchFavorite>,
                            private val listener: (ItemClickListener))
    : RecyclerView.Adapter<MatchFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchFavoriteAdapter.ViewHolder {
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

    fun getData(): List<MatchFavorite> = event_list

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var tvDateEvent: TextView
        private lateinit var tvHomeTeam: TextView
        private lateinit var tvHomeScore: TextView
        private lateinit var tvAwayTeam: TextView
        private lateinit var tvAwayScore: TextView

        fun bindItem(event_list: MatchFavorite) {

            tvDateEvent = itemView.findViewById(R.id.tv_date_event)
            tvHomeTeam = itemView.findViewById(R.id.tv_home_team)
            tvHomeScore = itemView.findViewById(R.id.tv_home_score)
            tvAwayTeam = itemView.findViewById(R.id.tv_away_team)
            tvAwayScore = itemView.findViewById(R.id.tv_away_score)

            tvDateEvent.text = toLocalDate(event_list.dateEvent)
            tvHomeTeam.text = event_list.homeTeam
            val homescore = event_list.homeScore ?: ""
            tvHomeScore.text = homescore.toString()
            tvAwayTeam.text = event_list.awayTeam

            val awayscore = event_list.awayScore ?: ""
            tvAwayScore.text = awayscore.toString()
        }
    }

    interface ItemClickListener {
        fun onItemClick(v: View, position: Int)
    }
}