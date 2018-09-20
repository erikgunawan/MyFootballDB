package id.ergun.myfootballdb.modules.matchschedule

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        @SerializedName("idEvent")
        var idEvent: Int? = null,
        @SerializedName("idSoccerXML")
        var idSoccerXML: Int? = null,
        @SerializedName("strEvent")
        var strEvent: String? = null,
        @SerializedName("strFilename")
        var strFilename: String? = null,
        @SerializedName("strSport")
        var strSport: String? = null,
        @SerializedName("idLeague")
        var idLeague: Int? = null,
        @SerializedName("strLeague")
        var strLeague: String? = null,
        @SerializedName("strSeason")
        var strSeason: String? = null,
        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,
        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,
        @SerializedName("intHomeScore")
        var intHomeScore: Int? = null,
        @SerializedName("intRound")
        var intRound: Int? = null,
        @SerializedName("intAwayScore")
        var intAwayScore: Int? = null,
        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null,
        @SerializedName("strHomeRedCards")
        var strHomeRedCards: String? = null,
        @SerializedName("strHomeYellowCards")
        var strHomeYellowCards: String? = null,
        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,
        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,
        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = null,
        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,
        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null,
        @SerializedName("strHomeFormation")
        var strHomeFormation: String? = null,
        @SerializedName("strAwayRedCards")
        var strAwayRedCards: String? = null,
        @SerializedName("strAwayYellowCards")
        var strAwayYellowCards: String? = null,
        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,
        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,
        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = null,
        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,
        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null,
        @SerializedName("strAwayFormation")
        var strAwayFormation: String? = null,
        @SerializedName("intHomeShots")
        var intHomeShots: Int? = null,
        @SerializedName("intAwayShots")
        var intAwayShots: Int? = null,
        @SerializedName("dateEvent")
        var dateEvent: String? = null,
        @SerializedName("strDate")
        var strDate: String? = null,
        @SerializedName("strTime")
        var strTime: String? = null,
        @SerializedName("idHomeTeam")
        var idHomeTeam: Int? = null,
        @SerializedName("idAwayTeam")
        var idAwayTeam: Int? = null,
        @SerializedName("strLocked")
        var strLocked: String? = null
): Parcelable