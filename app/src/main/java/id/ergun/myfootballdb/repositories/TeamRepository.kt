package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface TeamRepository {
    fun getAllTeamsById(id: String): Observable<DTOTeamList>
    fun getAllTeamsByName(name: String): Observable<DTOTeamList>
    fun getDetailTeam(id: String): Observable<DTOTeamList>
    fun searchTeams(name: String): Observable<DTOTeamList>
}

class TeamRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): TeamRepository {

    private var compositeDisposable: CompositeDisposable? = null

    override fun getAllTeamsById(id: String): Observable<DTOTeamList> {
        return apiService.getAllTeamsById(id)
    }

    override fun getAllTeamsByName(name: String): Observable<DTOTeamList> {
        return apiService.getAllTeamsByName(name)
    }

    override fun getDetailTeam(id: String): Observable<DTOTeamList> {
        return apiService.getDetailTeam(id)
    }

    override fun searchTeams(name: String): Observable<DTOTeamList> {
        return apiService.searchTeams(name)
    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }
}