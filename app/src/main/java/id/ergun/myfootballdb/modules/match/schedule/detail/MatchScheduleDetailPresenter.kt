package id.ergun.myfootballdb.modules.match.schedule.detail

import android.util.Log
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
import id.ergun.myfootballdb.repositories.TeamRepositoryImpl
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MatchScheduleDetailPresenter(private val view: MatchScheduleDetailContract.View,
                                   private val eventRepository: EventRepositoryImpl = EventRepositoryImpl(),
                                   private val teamRepository: TeamRepositoryImpl = TeamRepositoryImpl(),
                                   private var schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                                   private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): MatchScheduleDetailContract.Presenter {

    fun getDetailEvent(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                eventRepository.getDetailEvent(id)
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
                teamRepository.getDetailTeam(id)
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
                teamRepository.getDetailTeam(id)
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

    override fun onAttach(view: BaseView) {
        eventRepository.onAttach()
    }

    override fun onDetach() {
        eventRepository.onDetach()
    }
}