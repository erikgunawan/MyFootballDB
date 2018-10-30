package id.ergun.myfootballdb.modules.match.schedule.detail

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.Event

class MatchScheduleDetailContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showData(event: Event)
        fun showHomeBadge(url: String?)
        fun showAwayBadge(url: String?)
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}