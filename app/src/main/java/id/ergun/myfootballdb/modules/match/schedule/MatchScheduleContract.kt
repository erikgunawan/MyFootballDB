package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.views.BaseView

class MatchScheduleContract {
    interface View {
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}