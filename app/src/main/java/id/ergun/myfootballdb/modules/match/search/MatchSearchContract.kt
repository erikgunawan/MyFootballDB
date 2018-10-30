package id.ergun.myfootballdb.modules.match.search

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.Event

class MatchSearchContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showDataList(data: List<Event>)
        fun resetDataList()
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}