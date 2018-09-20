package id.ergun.myfootballdb.modules.matchschedule.detail

import android.util.Log
import id.ergun.myfootballdb.configs.RetrofitClient
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.matchschedule.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchScheduleDetailPresenter(private val view: MatchScheduleDetailView) {

    fun getDetailEvent(event: Event) {
        view.showLoading()
        RetrofitClient.getApiService().getDetailEvent(event.idEvent.toString())
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
        RetrofitClient.getApiService().getDetailTeam(event.idHomeTeam.toString())
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

        RetrofitClient.getApiService().getDetailTeam(event.idAwayTeam.toString())
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
    }
}