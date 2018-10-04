package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.views.BaseView

interface MatchScheduleView: BaseView, EventListRepositoryCallback {
    fun showLoading()
    fun hideLoading()
    fun showDataList(data: DTOEventList)
    fun resetDataList()
}