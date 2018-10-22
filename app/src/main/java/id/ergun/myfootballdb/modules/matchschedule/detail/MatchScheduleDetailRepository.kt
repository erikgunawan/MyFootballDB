package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface MatchScheduleDetailRepository {
    fun getDetailEvent(id: String): Observable<DTOEventList>
    fun getDetailHomeTeam(id: String): Observable<DTOTeamList>
    fun getDetailAwayTeam(id: String): Observable<DTOTeamList>
    fun getDetailTeam(id: String): Observable<DTOTeamList>
}

class MatchScheduleDetailRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): MatchScheduleDetailRepository {

    private var compositeDisposable: CompositeDisposable? = null

    override fun getDetailEvent(id: String): Observable<DTOEventList> {
        return apiService.getDetailEvent(id)
    }

    override fun getDetailTeam(id: String): Observable<DTOTeamList> {
        return apiService.getDetailTeam(id)
    }

    override fun getDetailHomeTeam(id: String): Observable<DTOTeamList> {
        return apiService.getDetailTeam(id)
    }

    override fun getDetailAwayTeam(id: String): Observable<DTOTeamList> {
        return apiService.getDetailTeam(id)
    }

//    fun getDetailEvent(id: String, callback: EventListRepositoryCallback) {
//      compositeDisposable?.add(apiService.getDetailEvent(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { events ->
//                            callback.onDataLoaded(events)
//                        },
//                        {
//                            callback.onDataError()
//                        }
//                )
//        )
//
//    }
//
//    fun getDetailHomeTeam(id: String, callback: TeamListRepositoryCallback) {
//        compositeDisposable?.add(apiService.getDetailTeam(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { teams ->
//                            callback.onDataLoaded(teams, HOME)
//                        },
//                        {
//                            callback.onDataError(HOME)
//                        }
//                )
//        )
//    }
//
//    fun getDetailAwayTeam(id: String, callback: TeamListRepositoryCallback) {
//        compositeDisposable?.add(apiService.getDetailTeam(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { teams ->
//                            callback.onDataLoaded(teams, AWAY)
//                        },
//                        {
//                            callback.onDataError(AWAY)
//                        }
//                )
//        )
//    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }

}