package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.Team

data class DTOTeamList(

        @SerializedName("teams")
        val data: List<Team>
)
