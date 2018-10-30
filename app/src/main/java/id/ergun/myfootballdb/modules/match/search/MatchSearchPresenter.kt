package id.ergun.myfootballdb.modules.match.search

import android.util.Log
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.modules.match.schedule.MatchScheduleContract
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MatchSearchPresenter(private val view: MatchSearchContract.View,
                           private val eventRepository: EventRepositoryImpl = EventRepositoryImpl(),
                           private val schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                           private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): MatchScheduleContract.Presenter {

    fun searchEvents(name: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                eventRepository.searchEvents(name)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { events ->
                                    view.showDataList(events.data.asSequence().sortedWith(compareByDescending {it.dateEvent}).filter { it.strSport.equals("Soccer") }.toList())
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