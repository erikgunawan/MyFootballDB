package id.ergun.myfootballdb.modules.team

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import id.ergun.myfootballdb.bases.adapters.BaseTeamAdapter
import id.ergun.myfootballdb.bases.models.DTOLeagueList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.modules.team.detail.TeamDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment: Fragment(), TeamContract.View,
        BaseTeamAdapter.ItemClickListener {

    private lateinit var presenter: TeamPresenter

    private lateinit var view: TeamUI<TeamFragment>

    private var leagues: MutableList<String> = mutableListOf()

    private lateinit var adapterLeague: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = TeamUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = TeamPresenter(this)
        presenter.onAttach(this)
        presenter.getAllLeagues()

        adapterLeague = ArrayAdapter(ctx, R.layout.simple_spinner_dropdown_item, leagues)
        view.spinLeague.adapter = adapterLeague
        view.spinLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.getAllTeamsByName(parent.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.adapter = BaseTeamAdapter(view.teamList, this)
        view.rvTeam.adapter = view.adapter

        return rootView
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<TeamDetailActivity>(TEAM to view.adapter.getData()[position])
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showLeagueList(data: DTOLeagueList) {
        leagues.clear()

        for (league: League in data.data) {
            leagues.add(league.strLeague)
        }

        adapterLeague.notifyDataSetChanged()

        if (data.data.isNotEmpty())
            presenter.getAllTeamsByName(view.spinLeague.selectedItem.toString())
    }

    override fun resetLeagueList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamList(data: DTOTeamList) {
        view.teamList.clear()
        view.teamList.addAll(data.data)

        view.adapter.notifyDataSetChanged()
    }

    override fun resetTeamList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}