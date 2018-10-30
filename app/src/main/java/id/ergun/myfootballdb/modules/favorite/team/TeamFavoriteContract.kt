package id.ergun.myfootballdb.modules.favorite.team

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.TeamFavorite

class TeamFavoriteContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showDataList(data: List<TeamFavorite>)
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}