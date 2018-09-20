package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.modules.matchschedule.Event

data class DTOEventList(
        @SerializedName("events")
        val data: List<Event>
)