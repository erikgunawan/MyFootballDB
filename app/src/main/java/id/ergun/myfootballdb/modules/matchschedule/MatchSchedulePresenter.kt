package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.views.BaseView

class MatchSchedulePresenter(private val view: MatchScheduleView,
                             private val matchScheduleRepository: MatchScheduleRepository = MatchScheduleRepository()): BasePresenter<BaseView> {

    fun getEventsNextLeague(id: String) {
        view.showLoading()

        matchScheduleRepository
                .getEventsNextLeague(id, object : EventListRepositoryCallback {
                    override fun onDataLoaded(data: DTOEventList) {
                        view.onDataLoaded(data)
                    }

                    override fun onDataError() {
                        view.onDataError()
                    }
                })
    }

    fun getEventsPastLeague(id: String) {
        view.showLoading()
        matchScheduleRepository
                .getEventsPastLeague(id, object : EventListRepositoryCallback {
                    override fun onDataLoaded(data: DTOEventList) {
                        view.onDataLoaded(data)
                    }

                    override fun onDataError() {
                        view.onDataError()
                    }
                })
    }

    override fun onAttach(view: BaseView) {
        matchScheduleRepository.onAttach()
    }

    override fun onDetach() {
        matchScheduleRepository.onDetach()
    }
}