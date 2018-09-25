package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.views.BaseView

interface MatchScheduleView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun showDataList(data: DTOEventList)
    fun resetDataList()
}