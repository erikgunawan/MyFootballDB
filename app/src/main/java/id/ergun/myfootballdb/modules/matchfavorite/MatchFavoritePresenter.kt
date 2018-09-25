package id.ergun.myfootballdb.modules.matchfavorite

import android.content.Context
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.MatchFavorite
import id.ergun.myfootballdb.db.database
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MatchFavoritePresenter(private val context: Context?, private val view: MatchFavoriteView): BasePresenter<BaseView> {

    private var compositeDisposable: CompositeDisposable? = null

    fun getFavoriteMatch() {
        view.showLoading()
        context?.database?.use {
            view.hideLoading()
            val result = select(MatchFavorite.TABLE_MATCH_FAVORITE)
            val matchFavorites = result.parseList(classParser<MatchFavorite>())
            view.showDataList(matchFavorites)
        }

    }

    override fun onAttach(view: BaseView) {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDetach() {
        compositeDisposable?.dispose()
    }

}