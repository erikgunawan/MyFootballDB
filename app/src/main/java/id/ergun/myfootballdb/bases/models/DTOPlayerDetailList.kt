package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.Player


data class DTOPlayerDetailList(

        @SerializedName("players")
        val data: List<Player>
)