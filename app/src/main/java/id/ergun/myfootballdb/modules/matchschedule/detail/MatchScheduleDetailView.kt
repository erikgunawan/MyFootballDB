package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.repositories.TeamListRepositoryCallback
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.modules.matchschedule.Event

interface MatchScheduleDetailView: BaseView,
        EventListRepositoryCallback,
        TeamListRepositoryCallback {

    fun showLoading()
    fun hideLoading()
    fun showData(event: Event)
    fun showHomeBadge(url: String?)
    fun showAwayBadge(url: String?)
}