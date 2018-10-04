package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchScheduleRepository {

    private var compositeDisposable: CompositeDisposable? = null

    private val apiService by lazy {
        RetrofitClient().create()
    }

    fun getEventsNextLeague(id: String, callback: EventListRepositoryCallback) {
        compositeDisposable?.add(
                apiService.getEventsNextLeague(id)
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

    fun getEventsPastLeague(id: String, callback: EventListRepositoryCallback) {
        compositeDisposable?.add(
                apiService.getEventsPastLeague(id)
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

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }

}