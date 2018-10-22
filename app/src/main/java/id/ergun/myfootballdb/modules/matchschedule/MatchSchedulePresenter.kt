package id.ergun.myfootballdb.modules.matchschedule

import android.util.Log
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MatchSchedulePresenter(private val view: MatchScheduleView,
                             private val matchScheduleRepository: MatchScheduleRepositoryImpl = MatchScheduleRepositoryImpl(),
                             private val schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                             private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): BasePresenter<BaseView> {

//    private var compositeDisposable: CompositeDisposable? = null

//    private val apiService by lazy {
//        RetrofitClient().create()
//    }
//
//    fun getEventsNextLeague(id: String, schedulerProvider: SchedulerProvider = AppSchedulerProvider()) {
//        view.showLoading()
//
//        compositeDisposable?.add(
//                apiService.getEventsNextLeague(id)
//                        .subscribeOn(schedulerProvider.io())
//                        .observeOn(schedulerProvider.ui())
//                        .subscribe(
//                                { events ->
//                                    view.showDataList(events)
//                                },
//                                {
//                                    view.onDataError()
//                                }
//                        )
//        )
//    }

    fun getEventsNextLeague(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                matchScheduleRepository.getEventsNextLeague(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showDataList(events)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()

                                },
                                {
                                    view.hideLoading()
                                    Log.e("EventsNextLeague", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun getEventsPastLeague(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                matchScheduleRepository.getEventsPastLeague(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showDataList(events)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    view.hideLoading()
                                    Log.e("EventsPastLeague", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

//    fun getEventsNextLeague(id: String) {
//        view.showLoading()
//
//        matchScheduleRepository
//                .getEventsNextLeague(id, object : EventListRepositoryCallback {
//                    override fun onDataLoaded(data: DTOEventList) {
//                        view.onDataLoaded(data)
//                    }
//
//                    override fun onDataError() {
//                        view.onDataError()
//                    }
//                })
//    }
//
//    fun getEventsPastLeague(id: String) {
//        view.showLoading()
//        matchScheduleRepository
//                .getEventsPastLeague(id, object : EventListRepositoryCallback {
//                    override fun onDataLoaded(data: DTOEventList) {
//                        view.onDataLoaded(data)
//                    }
//
//                    override fun onDataError() {
//                        view.onDataError()
//                    }
//                }, schedulerProvider)
//    }

    override fun onAttach(view: BaseView) {
        matchScheduleRepository.onAttach()
    }

    override fun onDetach() {
        matchScheduleRepository.onDetach()
    }
}