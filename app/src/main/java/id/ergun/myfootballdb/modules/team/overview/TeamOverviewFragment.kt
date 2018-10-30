package id.ergun.myfootballdb.modules.team.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.models.Team
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class TeamOverviewFragment: Fragment() {

    private lateinit var view: TeamOverviewUI<TeamOverviewFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = TeamOverviewUI()

        return view.createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments?.getParcelable(TEAM)
        fillView(team)
    }

    private fun fillView(team: Team?) {
        view.tvTeamOverview.text = team?.strDescriptionEN
    }
}