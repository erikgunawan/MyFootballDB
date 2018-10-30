package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.bases.models.DTOLeagueList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface LeagueRepository {
    fun getAllLeagues(): Observable<DTOLeagueList>
}

class LeagueRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): LeagueRepository {

    private var compositeDisposable: CompositeDisposable? = null

    override fun getAllLeagues(): Observable<DTOLeagueList> {
        return apiService.getAllLeagues()
    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }
}