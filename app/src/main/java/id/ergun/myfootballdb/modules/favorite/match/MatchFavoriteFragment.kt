package id.ergun.myfootballdb.modules.favorite.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.db.MatchFavorite
import id.ergun.myfootballdb.db.MatchFavorite.Mapper.toEvent
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class MatchFavoriteFragment: Fragment(), MatchFavoriteContract.View,
        BaseMatchAdapter.ItemClickListener {

    private lateinit var presenter: MatchFavoritePresenter

    private lateinit var view: MatchFavoriteBaseUI<MatchFavoriteFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = MatchFavoriteBaseUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = MatchFavoritePresenter(context, this)
        presenter.onAttach(this)

        view.swipeRefresh.onRefresh {
            view.swipeRefresh.isRefreshing = false
            presenter.getFavoriteMatch()
        }

        view.adapter = BaseMatchAdapter(view.eventList, this)
        view.rvEvent.adapter = view.adapter

        presenter.getFavoriteMatch()
        return rootView
    }

    override fun onItemClick(v: View, position: Int) {
        val event: Event = view.adapter.getData()[position]
        startActivity<MatchScheduleDetailActivity>(EVENT to event)
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showDataList(data: List<MatchFavorite>) {
        view.eventList.clear()

        for (matchFavorite: MatchFavorite in data) {
            view.eventList.add(toEvent(matchFavorite))
        }

        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}