package id.ergun.myfootballdb.bases.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.R.id.iv_team_badge
import id.ergun.myfootballdb.R.id.tv_team_name
import id.ergun.myfootballdb.bases.ui.match.BaseTeamAdapterUI
import id.ergun.myfootballdb.models.Team
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class BaseTeamAdapter(private val teams: List<Team>,
                      private val listener: (BaseTeamAdapter.ItemClickListener)): RecyclerView.Adapter<BaseTeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = TeamViewHolder(BaseTeamAdapterUI().createView(AnkoContext.create(parent.context, parent)))
        view.itemView.setOnClickListener {
            listener.onItemClick(it, view.adapterPosition)
        }
        return view
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) = holder.bindItem(teams[position])

    fun getData(): List<Team> = teams

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val teamBadge: ImageView = view.find(iv_team_badge)
        private val teamName: TextView = view.find(tv_team_name)

        fun bindItem(team: Team) {
            Glide.with(itemView.context).load(team.strTeamBadge).into(teamBadge)
            teamName.text = team.strTeam
        }
    }

    interface ItemClickListener {
        fun onItemClick(v: View, position: Int)
    }
}