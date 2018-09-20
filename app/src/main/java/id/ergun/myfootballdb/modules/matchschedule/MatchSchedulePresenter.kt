package id.ergun.myfootballdb.modules.matchschedule

import android.util.Log
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchSchedulePresenter(private val view: MatchScheduleView) {

    fun getEventsNextLeague(id: String) {
        view.showLoading()
        RetrofitClient.getApiService().getEventsNextLeague(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { events ->
                            Log.d("log", events.toString())
                            view.hideLoading()
                            view.showDataList(events)
                        },
                        { error ->
                            view.hideLoading()
                            Log.e("Error", error.message)
                        }
                )
    }

    fun getEventsPastLeague(id: String) {
        view.showLoading()
        RetrofitClient.getApiService().getEventsPastLeague(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { events ->
                            Log.d("log", events.toString())
                            view.hideLoading()
                            view.showDataList(events)
                        },
                        { error ->
                            view.hideLoading()
                            Log.e("Error", error.message)
                        }
                )
    }
}