package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.modules.matchschedule.Event

interface MatchScheduleDetailView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun showData(event: Event)
    fun showHomeBadge(url: String?)
    fun showAwayBadge(url: String?)
}