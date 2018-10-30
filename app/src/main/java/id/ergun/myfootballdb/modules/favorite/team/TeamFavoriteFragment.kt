package id.ergun.myfootballdb.modules.favorite.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.bases.adapters.BaseTeamAdapter
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.db.TeamFavorite
import id.ergun.myfootballdb.db.TeamFavorite.Mapper.toTeam
import id.ergun.myfootballdb.modules.team.detail.TeamDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamFavoriteFragment: Fragment(), TeamFavoriteContract.View,
        BaseTeamAdapter.ItemClickListener {

    private lateinit var presenter: TeamFavoritePresenter

    private lateinit var view: TeamFavoriteUI<TeamFavoriteFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = TeamFavoriteUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = TeamFavoritePresenter(context, this)
        presenter.onAttach(this)

        view.swipeRefresh.onRefresh {
            view.swipeRefresh.isRefreshing = false
            presenter.getFavoriteMatch()
        }

        view.adapter = BaseTeamAdapter(view.teamList, this)
        view.rvTeam.adapter = view.adapter

        presenter.getFavoriteMatch()
        return rootView
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<TeamDetailActivity>(TEAM to view.adapter.getData()[position])
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showDataList(data: List<TeamFavorite>) {
        view.teamList.clear()

        for (teamFavorite: TeamFavorite in data) {
            view.teamList.add(toTeam(teamFavorite))
        }

        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}