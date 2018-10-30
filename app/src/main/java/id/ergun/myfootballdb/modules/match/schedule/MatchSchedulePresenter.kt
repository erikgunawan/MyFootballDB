package id.ergun.myfootballdb.modules.match.schedule

import android.util.Log
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
import id.ergun.myfootballdb.repositories.LeagueRepositoryImpl
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MatchSchedulePresenter(private val view: MatchScheduleContract.View,
                             private val leagueRepository: LeagueRepositoryImpl = LeagueRepositoryImpl(),
                             private val eventRepository: EventRepositoryImpl = EventRepositoryImpl(),
                             private val schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                             private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): MatchScheduleContract.Presenter {

    fun getAllLeagues() {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                leagueRepository.getAllLeagues()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { data ->
                                    val leagues = data.data.filter { it.strSport.equals("Soccer") }
                                    view.showLeagueList(leagues)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()

                                },
                                {
                                    view.hideLoading()
//                                    view.showLeagueList(DTOLeagueList(mutableListOf()))
                                    Log.e("AllLeagues", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun getEventsNextLeague(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                eventRepository.getEventsNextLeague(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showDataList(events.data.sortedWith(compareByDescending {it.dateEvent}))
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
                eventRepository.getEventsPastLeague(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showDataList(events.data.sortedWith(compareByDescending {it.dateEvent}))
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

    override fun onAttach(view: BaseView) {
        eventRepository.onAttach()
    }

    override fun onDetach() {
        eventRepository.onDetach()
    }
}