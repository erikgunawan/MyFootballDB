package id.ergun.myfootballdb.models

import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague")
        val idLeague: Int? = null,
        @SerializedName("strLeague")
        val strLeague: String? = null,
        @SerializedName("strSport")
        val strSport: String? = null,
        @SerializedName("strLeagueAlternate")
        val strLeagueAlternate: String? = null
)