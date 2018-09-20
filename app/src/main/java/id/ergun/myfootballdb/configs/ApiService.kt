package id.ergun.myfootballdb.configs

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("eventspastleague.php")
    fun getEventsPastLeague(@Query("id") id: String): Observable<DTOEventList>

    @GET("eventsnextleague.php")
    fun getEventsNextLeague(@Query("id") id: String): Observable<DTOEventList>

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id: String): Observable<DTOEventList>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Observable<DTOTeamList>

}