package id.ergun.myfootballdb.modules.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.bases.adapters.BasePlayerAdapter
import id.ergun.myfootballdb.bases.models.DTOPlayerList
import id.ergun.myfootballdb.configs.PLAYER
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.player.detail.PlayerDetailActivity
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment: Fragment(), PlayerContract.View,
        BasePlayerAdapter.ItemClickListener {

    private lateinit var presenter: PlayerPresenter

    private lateinit var view: PlayerUI<PlayerFragment>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = PlayerUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = PlayerPresenter(this)
        presenter.onAttach(this)

        view.adapter = BasePlayerAdapter(view.playerList, this)
        view.rvPlayer.adapter = view.adapter

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments?.getParcelable(TEAM)
        fillView(team)

        view.swipeRefresh.onRefresh {
            resetDataList()
            view.swipeRefresh.isRefreshing = false
            presenter.getAllPlayers(team?.idTeam.toString())
        }
    }

    private fun fillView(team: Team?) {
        presenter.getAllPlayers(team?.idTeam.toString())
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<PlayerDetailActivity>(PLAYER to view.adapter.getData()[position])
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showDataList(data: DTOPlayerList) {
        view.playerList.clear()
        view.playerList.addAll(data.data)
        view.adapter.notifyDataSetChanged()
    }

    override fun resetDataList() {
        view.playerList.clear()
        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}