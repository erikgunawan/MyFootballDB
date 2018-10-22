package id.ergun.myfootballdb.modules.matchschedule.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.configs.LEAGUE_ID
import id.ergun.myfootballdb.modules.matchschedule.MatchScheduleAdapter
import id.ergun.myfootballdb.modules.matchschedule.MatchSchedulePresenter
import id.ergun.myfootballdb.modules.matchschedule.MatchScheduleView
import id.ergun.myfootballdb.modules.matchschedule.detail.MatchScheduleDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class MatchSchedulePrevFragment: Fragment(), MatchScheduleView,
        MatchScheduleAdapter.ItemClickListener {

//    override fun onDataLoaded(data: DTOEventList) {
//        hideLoading()
//        showDataList(data)
//    }
//
//    override fun onDataError() {
//        hideLoading()
//    }

    private lateinit var presenter: MatchSchedulePresenter

    private lateinit var view: MatchScheduleBaseUI<MatchSchedulePrevFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = MatchScheduleBaseUI()

        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = MatchSchedulePresenter(this)
        presenter.onAttach(this)
        presenter.getEventsPastLeague(LEAGUE_ID)

        view.swipeRefresh.onRefresh {
            resetDataList()
            view.swipeRefresh.isRefreshing = false
            presenter.getEventsPastLeague(LEAGUE_ID)
        }

        view.adapter = MatchScheduleAdapter(view.eventList, this)
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

    override fun resetDataList() {
        view.eventList.clear()
        view.adapter.notifyDataSetChanged()
    }

    override fun showDataList(data: DTOEventList) {
        view.eventList.clear()
        view.eventList.addAll(data.data.sortedWith(compareByDescending {it.dateEvent}))
        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}