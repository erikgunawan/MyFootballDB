package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.bases.presenters.BasePresenter
import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.repositories.TeamListRepositoryCallback
import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.configs.AWAY
import id.ergun.myfootballdb.configs.HOME

class MatchScheduleDetailPresenter(private val view: MatchScheduleDetailView,
                                   private val matchScheduleDetailRepository: MatchScheduleDetailRepository = MatchScheduleDetailRepository()): BasePresenter<BaseView> {

    fun getDetailEvent(id: String) {
        view.showLoading()

        matchScheduleDetailRepository
                .getDetailEvent(id, object : EventListRepositoryCallback {
                    override fun onDataLoaded(data: DTOEventList) {
                        view.onDataLoaded(data)
                    }

                    override fun onDataError() {
                        view.onDataError()
                    }
                })

    }

    fun getDetailTeam(id: String, side: String) {
        if (side == HOME) {
            matchScheduleDetailRepository
                    .getDetailHomeTeam(id, object : TeamListRepositoryCallback {
                        override fun onDataLoaded(data: DTOTeamList, side: String) {
                            view.onDataLoaded(data, side)
                        }

                        override fun onDataError(side: String) {
                            view.onDataError(side)
                        }
                    })
        }
        else if (side == AWAY) {
            matchScheduleDetailRepository
                    .getDetailAwayTeam(id, object : TeamListRepositoryCallback {
                        override fun onDataLoaded(data: DTOTeamList, side: String) {
                            view.onDataLoaded(data, side)
                        }

                        override fun onDataError(side: String) {
                            view.onDataError(side)
                        }
                    })
        }

    }

    override fun onAttach(view: BaseView) {
        matchScheduleDetailRepository.onAttach()
    }

    override fun onDetach() {
        matchScheduleDetailRepository.onDetach()
    }
}