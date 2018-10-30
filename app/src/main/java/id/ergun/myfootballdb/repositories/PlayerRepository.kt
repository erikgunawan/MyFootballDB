package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.bases.models.DTOPlayerDetailList
import id.ergun.myfootballdb.bases.models.DTOPlayerList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface PlayerRepository {
    fun getAllPlayers(id: String): Observable<DTOPlayerList>
    fun getDetailPlayer(id: String): Observable<DTOPlayerDetailList>
}

class PlayerRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): PlayerRepository {

    private var compositeDisposable: CompositeDisposable? = null

    override fun getAllPlayers(id: String): Observable<DTOPlayerList> {
        return apiService.getAllPlayers(id)
    }

    override fun getDetailPlayer(id: String): Observable<DTOPlayerDetailList> {
        return apiService.getDetailPlayer(id)
    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }
}