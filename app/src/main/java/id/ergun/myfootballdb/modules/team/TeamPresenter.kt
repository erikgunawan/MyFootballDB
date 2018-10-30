package id.ergun.myfootballdb.modules.team

import android.util.Log
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.repositories.LeagueRepositoryImpl
import id.ergun.myfootballdb.repositories.TeamRepositoryImpl
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class TeamPresenter(private val view: TeamContract.View,
                    private val leagueRepository: LeagueRepositoryImpl = LeagueRepositoryImpl(),
                    private val teamRepository: TeamRepositoryImpl = TeamRepositoryImpl(),
                    private val schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): TeamContract.Presenter {

    fun getAllLeagues() {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                leagueRepository.getAllLeagues()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { leagues ->
                                    view.showLeagueList(leagues.data.filter { it.strSport.equals("Soccer") })
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

    fun getAllTeamsById(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()

        compositeDisposable?.add(
                teamRepository.getAllTeamsById(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { teams ->
                                    view.showTeamList(teams.data)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    view.hideLoading()
                                    view.showTeamList(mutableListOf())
                                    Log.e("AllTeamsById", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun getAllTeamsByName(name: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()

        compositeDisposable?.add(
                teamRepository.getAllTeamsByName(name)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { teams ->
                                    view.showTeamList(teams.data)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()

                                },
                                {
                                    view.hideLoading()
                                    view.showTeamList(mutableListOf())
                                    Log.e("AllTeamsByName", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    fun searchTeams(name: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()

        compositeDisposable?.add(
                teamRepository.searchTeams(name)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { teams ->
                                    view.showTeamList(teams.data.filter { it.strSport.equals("Soccer") })
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()
                                },
                                {
                                    view.hideLoading()
                                    view.showTeamList(mutableListOf())
                                    Log.e("SearchTeam", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    override fun onAttach(T: BaseView) {
        leagueRepository.onAttach()
        teamRepository.onAttach()
    }

    override fun onDetach() {
        leagueRepository.onDetach()
        teamRepository.onDetach()
    }
}