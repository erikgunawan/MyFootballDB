package id.ergun.myfootballdb.modules.player

import android.util.Log
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.repositories.PlayerRepositoryImpl
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import id.ergun.myfootballdb.utils.scheduler.AppSchedulerProvider
import id.ergun.myfootballdb.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PlayerPresenter(private val view: PlayerContract.View,
                      private val playerRepository: PlayerRepositoryImpl = PlayerRepositoryImpl(),
                      private val schedulerProvider: SchedulerProvider = AppSchedulerProvider(),
                      private var compositeDisposable: CompositeDisposable? = CompositeDisposable()): PlayerContract.Presenter {


    fun getAllPlayers(id: String) {
        EspressoTestingIdlingResource.increment()
        view.showLoading()
        compositeDisposable?.add(
                playerRepository.getAllPlayers(id)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                { players ->
                                    view.showDataList(players)
                                    view.hideLoading()
                                    EspressoTestingIdlingResource.decrement()

                                },
                                {
                                    view.hideLoading()
//                                    view.showLeagueList(DTOLeagueList(mutableListOf()))
                                    Log.e("AllPlayers", it.message.toString())
                                    EspressoTestingIdlingResource.decrement()
                                }
                        )
        )
    }

    override fun onAttach(T: BaseView) {
        playerRepository.onAttach()
    }

    override fun onDetach() {
        playerRepository.onDetach()
    }
}