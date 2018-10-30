package id.ergun.myfootballdb.modules.match.schedule

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.models.League

class MatchScheduleContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showLeagueList(data: List<League>)
        fun resetLeagueList()
        fun showDataList(data: List<Event>)
        fun resetDataList()
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}