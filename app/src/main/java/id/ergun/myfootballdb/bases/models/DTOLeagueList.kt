package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.League

data class DTOLeagueList(

        @SerializedName("leagues")
        val data: List<League>
)