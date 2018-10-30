package id.ergun.myfootballdb.bases.models

import com.google.gson.annotations.SerializedName
import id.ergun.myfootballdb.models.Event

data class DTOEventSearchList(
        @SerializedName("event")
        val data: List<Event>
)