package id.ergun.myfootballdb.modules.favorite.match

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.MatchFavorite


interface MatchFavoriteView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun showDataList(match_favorites: List<MatchFavorite>)
}