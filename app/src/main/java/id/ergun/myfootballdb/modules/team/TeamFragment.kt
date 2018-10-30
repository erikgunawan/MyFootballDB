package id.ergun.myfootballdb.modules.team

import android.R.layout.simple_spinner_dropdown_item
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import id.ergun.myfootballdb.R.id.action_search
import id.ergun.myfootballdb.R.menu.menu_search
import id.ergun.myfootballdb.bases.adapters.BaseTeamAdapter
import id.ergun.myfootballdb.bases.adapters.SpinnerLeagueAdapter
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.models.Team
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

    private var leagues: MutableList<League> = mutableListOf()

    private lateinit var adapterLeague: SpinnerLeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = TeamUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        setHasOptionsMenu(true)

        presenter = TeamPresenter(this)
        presenter.onAttach(this)
        presenter.getAllLeagues()

        adapterLeague = SpinnerLeagueAdapter(ctx, simple_spinner_dropdown_item, leagues)
        view.spinLeague.adapter = adapterLeague
        view.spinLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.getAllTeamsByName(adapterLeague.getItem(position).strLeague.toString())
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

    override fun showLeagueList(data: List<League>) {
        leagues.clear()
        leagues.addAll(data)
        adapterLeague.notifyDataSetChanged()

        if (data.isNotEmpty())
            presenter.getAllTeamsByName(adapterLeague.getItem(0).strLeague.toString())
    }

    override fun resetLeagueList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamList(data: List<Team>) {
        view.teamList.clear()
        view.teamList.addAll(data)
        view.adapter.notifyDataSetChanged()
    }

    override fun resetTeamList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(menu_search, menu)

        val searchView = menu?.findItem(action_search)?.actionView as SearchView?

        searchView?.queryHint = "Search teams"
        searchQuery(searchView)
    }

    private fun searchQuery(searchView: SearchView?) {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchTeams(newText.toString())
                return true
            }
        })
    }
}