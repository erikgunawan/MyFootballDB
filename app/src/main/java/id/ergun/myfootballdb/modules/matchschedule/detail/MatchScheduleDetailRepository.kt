package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.repositories.TeamListRepositoryCallback
import id.ergun.myfootballdb.configs.AWAY
import id.ergun.myfootballdb.configs.HOME
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchScheduleDetailRepository {

    private var compositeDisposable: CompositeDisposable? = null

    private val apiService by lazy {
        RetrofitClient().create()
    }

    fun getDetailEvent(id: String, callback: EventListRepositoryCallback) {
      compositeDisposable?.add(apiService.getDetailEvent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { events ->
                            callback.onDataLoaded(events)
                        },
                        {
                            callback.onDataError()
                        }
                )
        )

    }

    fun getDetailHomeTeam(id: String, callback: TeamListRepositoryCallback) {
        compositeDisposable?.add(apiService.getDetailTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teams ->
                            callback.onDataLoaded(teams, HOME)
                        },
                        {
                            callback.onDataError(HOME)
                        }
                )
        )
    }

    fun getDetailAwayTeam(id: String, callback: TeamListRepositoryCallback) {
        compositeDisposable?.add(apiService.getDetailTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teams ->
                            callback.onDataLoaded(teams, AWAY)
                        },
                        {
                            callback.onDataError(AWAY)
                        }
                )
        )
    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }

}