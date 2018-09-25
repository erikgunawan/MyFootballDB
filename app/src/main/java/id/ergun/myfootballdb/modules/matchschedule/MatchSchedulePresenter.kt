package id.ergun.myfootballdb.modules.matchschedule

import android.annotation.SuppressLint
import android.util.Log
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchSchedulePresenter(private val view: MatchScheduleView): BasePresenter<BaseView> {

    private var compositeDisposable: CompositeDisposable? = null

    private val apiService by lazy {
        RetrofitClient().create()
    }

    @SuppressLint("CheckResult")
    fun getEventsNextLeague(id: String) {
        view.showLoading()

        compositeDisposable?.add(apiService.getEventsNextLeague(id)
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
                ))
    }

    @SuppressLint("CheckResult")
    fun getEventsPastLeague(id: String) {
        view.showLoading()
        compositeDisposable?.add(apiService.getEventsPastLeague(id)
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
        )
    }

    override fun onAttach(view: BaseView) {
        compositeDisposable = CompositeDisposable()
    }


    override fun onDetach() {
        compositeDisposable?.dispose()
    }
}