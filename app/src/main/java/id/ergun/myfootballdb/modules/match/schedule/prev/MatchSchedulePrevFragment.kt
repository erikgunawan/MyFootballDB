package id.ergun.myfootballdb.modules.match.schedule.prev

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.bases.adapters.SpinnerLeagueAdapter
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.modules.match.schedule.MatchScheduleContract
import id.ergun.myfootballdb.modules.match.schedule.MatchSchedulePresenter
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class MatchSchedulePrevFragment: Fragment(), MatchScheduleContract.View,
        BaseMatchAdapter.ItemClickListener {

    private lateinit var presenter: MatchSchedulePresenter

    private lateinit var view: MatchSchedulePrevUI<MatchSchedulePrevFragment>

    private var leagues: MutableList<League> = mutableListOf()

    private lateinit var adapterLeague: SpinnerLeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = MatchSchedulePrevUI()

        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = MatchSchedulePresenter(this)
        presenter.onAttach(this)
        presenter.getAllLeagues()

//        view.swipeRefresh.onRefresh {
//            resetDataList()
//            view.swipeRefresh.isRefreshing = false
//            presenter.getEventsPastLeague(LEAGUE_ID)
//        }

        adapterLeague = SpinnerLeagueAdapter(ctx, R.layout.simple_spinner_dropdown_item, leagues)
        view.spinLeague.adapter = adapterLeague
        view.spinLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.getEventsPastLeague(adapterLeague.getItem(position).idLeague.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        view.adapter = BaseMatchAdapter(view.eventList, this)
        view.rvEvent.adapter = view.adapter
        return rootView
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<MatchScheduleDetailActivity>(EVENT to view.adapter.getData()[position])
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        leagues.clear()
        leagues.addAll(data)
        adapterLeague.notifyDataSetChanged()

        if (data.isNotEmpty())
            presenter.getEventsPastLeague(adapterLeague.getItem(0).idLeague.toString())
    }

    override fun resetLeagueList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetDataList() {
        view.eventList.clear()
        view.adapter.notifyDataSetChanged()
    }

    override fun showDataList(data: List<Event>) {
        view.eventList.clear()
        view.eventList.addAll(data)
        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}