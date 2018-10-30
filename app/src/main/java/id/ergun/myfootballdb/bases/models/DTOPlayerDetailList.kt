package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.Player


data class DTOPlayerList(

        @SerializedName("player")
        val data: List<Player>
)