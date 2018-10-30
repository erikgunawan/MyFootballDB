package id.ergun.myfootballdb.configs

import id.ergun.myfootballdb.bases.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("all_leagues.php")
    fun getAllLeagues(): Observable<DTOLeagueList>

    @GET("search_all_teams.php")
    fun getAllTeamsByName(@Query("l") name:String) : Observable<DTOTeamList>

    @GET("lookup_all_teams.php")
    fun getAllTeamsById(@Query("id") id:String) : Observable<DTOTeamList>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Observable<DTOTeamList>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") name: String): Observable<DTOTeamList>

    @GET("eventspastleague.php")
    fun getEventsPastLeague(@Query("id") id: String): Observable<DTOEventList>

    @GET("eventsnextleague.php")
    fun getEventsNextLeague(@Query("id") id: String): Observable<DTOEventList>

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id: String): Observable<DTOEventList>

    @GET("searchevents.php")
    fun searchEvents(@Query("e") name: String): Observable<DTOEventSearchList>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id: String): Observable<DTOPlayerList>

    @GET("lookupplayer.php")
    fun getDetailPlayer(@Query("id") id: String): Observable<DTOPlayerDetailList>

}