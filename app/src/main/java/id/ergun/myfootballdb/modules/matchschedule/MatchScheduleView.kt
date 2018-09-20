package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.models.DTOEventList

interface MatchScheduleView {
    fun showLoading()
    fun hideLoading()
    fun showDataList(data: DTOEventList)
    fun resetDataList()
}