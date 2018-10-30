package id.ergun.myfootballdb.modules.team

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.models.Team

class TeamContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showLeagueList(data: List<League>)
        fun resetLeagueList()
        fun showTeamList(data: List<Team>)
        fun resetTeamList()

    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}