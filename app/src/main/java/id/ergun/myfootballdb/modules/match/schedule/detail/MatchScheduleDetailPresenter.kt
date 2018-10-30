package id.ergun.myfootballdb.modules.matchschedule.detail

import android.util.Log
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MatchScheduleDetailPresenter(private val view: MatchScheduleDetailView,
                                   private val matchScheduleDetailRepository: MatchScheduleDetailRepositoryImpl = MatchScheduleDetailRepositoryImpl(),
                                   private var schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                                   private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): BasePresenter<BaseView> {

    fun getDetailEvent(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                matchScheduleDetailRepository.getDetailEvent(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showData(events.data[0])
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    view.hideLoading()
                                    Log.d("DetailEvent", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun getDetailHomeTeam(id: String) {
        EspressoTestingIdlingResource.increment()
        compositeDisposable?.add(
                matchScheduleDetailRepository.getDetailTeam(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { teams ->
                                    view.showHomeBadge(teams.data[0].strTeamBadge)
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    Log.e("DetailHomeTeam", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun getDetailAwayTeam(id: String) {
        EspressoTestingIdlingResource.increment()
        compositeDisposable?.add(
                matchScheduleDetailRepository.getDetailTeam(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { teams ->
                                    view.showAwayBadge(teams.data[0].strTeamBadge)
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    Log.e("DetailAwayTeam", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

//    fun getDetailEvent(id: String) {
//        view.showLoading()
//
//        matchScheduleDetailRepository
//                .getDetailEvent(id, object : EventListRepositoryCallback {
//                    override fun onDataLoaded(data: DTOEventList) {
//                        view.onDataLoaded(data)
//                    }
//
//                    override fun onDataError() {
//                        view.onDataError()
//                    }
//                })
//
//    }
//
//    fun getDetailTeam(id: String, side: String) {
//        if (side == HOME) {
//            matchScheduleDetailRepository
//                    .getDetailHomeTeam(id, object : TeamListRepositoryCallback {
//                        override fun onDataLoaded(data: DTOTeamList, side: String) {
//                            view.onDataLoaded(data, side)
//                        }
//
//                        override fun onDataError(side: String) {
//                            view.onDataError(side)
//                        }
//                    })
//        }
//        else if (side == AWAY) {
//            matchScheduleDetailRepository
//                    .getDetailAwayTeam(id, object : TeamListRepositoryCallback {
//                        override fun onDataLoaded(data: DTOTeamList, side: String) {
//                            view.onDataLoaded(data, side)
//                        }
//
//                        override fun onDataError(side: String) {
//                            view.onDataError(side)
//                        }
//                    })
//        }
//
//    }

    override fun onAttach(view: BaseView) {
        matchScheduleDetailRepository.onAttach()
    }

    override fun onDetach() {
        matchScheduleDetailRepository.onDetach()
    }
}