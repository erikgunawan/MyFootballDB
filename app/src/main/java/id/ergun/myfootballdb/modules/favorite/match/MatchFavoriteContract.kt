package id.ergun.myfootballdb.modules.favorite.match

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.MatchFavorite


class MatchFavoriteContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showDataList(data: List<MatchFavorite>)
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}