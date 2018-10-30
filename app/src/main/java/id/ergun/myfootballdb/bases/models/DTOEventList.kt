package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.Event

data class DTOEventList(
        @SerializedName("events")
        val data: List<Event>
)