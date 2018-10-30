package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOEventSearchList
import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface EventRepository {
    fun getEventsNextLeague(id: String): Observable<DTOEventList>
    fun getEventsPastLeague(id: String): Observable<DTOEventList>
    fun getDetailEvent(id: String): Observable<DTOEventList>
    fun searchEvents(name: String): Observable<DTOEventSearchList>
}

class EventRepositoryImpl(private var apiService: ApiService = RetrofitClient().create()): EventRepository {

    private var compositeDisposable: CompositeDisposable? = null

    override fun getEventsNextLeague(id: String): Observable<DTOEventList> {
        return apiService.getEventsNextLeague(id)
    }

    override fun getEventsPastLeague(id: String): Observable<DTOEventList> {
        return apiService.getEventsPastLeague(id)
    }

    override fun getDetailEvent(id: String): Observable<DTOEventList> {
        return apiService.getDetailEvent(id)
    }

    override fun searchEvents(name: String): Observable<DTOEventSearchList> {
        return apiService.searchEvents(name)
    }

    fun onAttach() {
        compositeDisposable = CompositeDisposable()
    }

    fun onDetach() {
        compositeDisposable?.dispose()
    }

}