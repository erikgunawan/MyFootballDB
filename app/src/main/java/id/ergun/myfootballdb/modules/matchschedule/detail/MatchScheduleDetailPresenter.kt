package id.ergun.myfootballdb.modules.matchschedule.detail

import android.annotation.SuppressLint
import android.util.Log
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.configs.RetrofitClient
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.matchschedule.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchScheduleDetailPresenter(private val view: MatchScheduleDetailView): BasePresenter<BaseView> {

    private var compositeDisposable: CompositeDisposable? = null

    private val apiService by lazy {
        RetrofitClient().create()
    }

    @SuppressLint("CheckResult")
    fun getDetailEvent(event: Event) {
        view.showLoading()
        compositeDisposable?.add(apiService.getDetailEvent(event.idEvent.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { events ->
                            Log.d("log", events.toString())
                            view.hideLoading()

                            val ev: Event = events.data[0]
                            view.showData(ev)
                        },
                        { error ->
                            view.hideLoading()
                            Log.e("Error", error.message)
                        }
                )
        )
        compositeDisposable?.add(apiService.getDetailTeam(event.idHomeTeam.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teams ->
                            val team: Team = teams.data[0]
                            view.showHomeBadge(team.strTeamBadge)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
        )

        compositeDisposable?.add(apiService.getDetailTeam(event.idAwayTeam.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { teams ->
                            val team: Team = teams.data[0]
                            view.showAwayBadge(team.strTeamBadge)
                        },
                        { error ->
                            Log.e("Error", error.message)
                        }
                )
        )
    }

    override fun onAttach(view: BaseView) {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDetach() {
        compositeDisposable?.dispose()
    }
}