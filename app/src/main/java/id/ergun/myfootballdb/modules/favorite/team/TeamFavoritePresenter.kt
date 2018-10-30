package id.ergun.myfootballdb.modules.favorite.team

import android.content.Context
import android.database.sqlite.SQLiteException
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.db.TeamFavorite
import id.ergun.myfootballdb.db.database
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TeamFavoritePresenter(private val context: Context?, private val view: TeamFavoriteContract.View): BasePresenter<BaseView> {

    private var compositeDisposable: CompositeDisposable? = null

    fun getFavoriteMatch() {
        view.showLoading()
        try {
            context?.database?.use {
                view.hideLoading()
                val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
                val teamFavorites = result.parseList(classParser<TeamFavorite>())
                view.showDataList(teamFavorites)
            }
        }
        catch (exception: SQLiteException) {
            view.hideLoading()
        }
    }

    override fun onAttach(view: BaseView) {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDetach() {
        compositeDisposable?.dispose()
    }

}