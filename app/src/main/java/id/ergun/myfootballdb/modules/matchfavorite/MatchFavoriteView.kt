package id.ergun.myfootballdb.modules.matchfavorite

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.MatchFavorite


interface MatchFavoriteView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun showDataList(match_favorites: List<MatchFavorite>)
}