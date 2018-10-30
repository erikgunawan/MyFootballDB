package id.ergun.myfootballdb.modules.team.detail

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.Team

class TeamDetailContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showData(data: Team)

    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }

}