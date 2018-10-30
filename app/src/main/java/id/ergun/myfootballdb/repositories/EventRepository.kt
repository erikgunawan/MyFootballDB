package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface MatchRepository {
    fun getEventsNextLeague(id: String): Observable<DTOEventList>
    fun getEventsPastLeague(id: String): Observable<DTOEventList>
}

class MatchRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): MatchRepository {

    private var compositeDisposable: CompositeDisposable? = null
//
//    private val apiService by lazy {
//        RetrofitClient().create()
//    }

    override fun getEventsNextLeague(id: String): Observable<DTOEventList> {
        return apiService.getEventsNextLeague(id)
    }

    override fun getEventsPastLeague(id: String): Observable<DTOEventList> {
        return apiService.getEventsPastLeague(id)
    }

//    override fun getEventsNextLeague(id: String, callback: EventListRepositoryCallback) {
//        compositeDisposable?.add(
//                apiService.getEventsNextLeague(id)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                { events ->
//                                    callback.onDataLoaded(events)
//                                },
//                                {
//                                    callback.onDataError()
//                                }
//                        )
//        )
//    }
//
//    override fun getEventsPastLeague(id: String, callback: EventListRepositoryCallback, schedulerProvider: SchedulerProvider) {
//        compositeDisposable?.add(
//                apiService.getEventsPastLeague(id)
//                .subscribeOn(schedulerProvider.io())
//                .observeOn(schedulerProvider.ui())
//                .subscribe(
//                        { events ->
//                            callback.onDataLoaded(events)
//                        },
//                        {
//                            callback.onDataError()
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