package id.ergun.myfootballdb.modules.matchschedule.detail

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import id.ergun.myfootballdb.bases.repositories.TeamListRepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchScheduleDetailPresenterTest {

    @Mock
    private lateinit var view: MatchScheduleDetailView

    @Mock
    private lateinit var repository: MatchScheduleDetailRepository

    @Mock
    private lateinit var events: DTOEventList

    @Mock
    private lateinit var homeTeams: DTOTeamList

    @Mock
    private lateinit var awayTeams: DTOTeamList

    private lateinit var presenter: MatchScheduleDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchScheduleDetailPresenter(view, repository)
    }

    @Test
    fun getDetailEventTest() {
        val idEvent = "576514"

        presenter.getDetailEvent(idEvent)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getDetailEvent(eq(idEvent), capture())
            firstValue.onDataLoaded(events)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(events)
    }

    @Test
    fun getDetailEventErrorTest() {
        val idEvent = ""

        presenter.getDetailEvent(idEvent)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getDetailEvent(eq(idEvent), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataError()
    }

    @Test
    fun getDetailHomeTeamTest() {
        val idHomeTeam = "134778"
        val side = "home"

        presenter.getDetailTeam(idHomeTeam, side)

        argumentCaptor<TeamListRepositoryCallback>().apply {
            verify(repository).getDetailHomeTeam(eq(idHomeTeam), capture())
            firstValue.onDataLoaded(homeTeams, side)
        }

        Mockito.verify(view).onDataLoaded(homeTeams, side)
    }

    @Test
    fun getDetailHomeTeamErrorTest() {
        val idHomeTeam = ""
        val side = "home"

        presenter.getDetailTeam(idHomeTeam, side)

        argumentCaptor<TeamListRepositoryCallback>().apply {
            verify(repository).getDetailHomeTeam(eq(idHomeTeam), capture())
            firstValue.onDataError(side)
        }

        Mockito.verify(view).onDataError(side)
    }

    @Test
    fun getDetailAwayTeamTest() {
        val idAwayTeam = "134778"
        val side = "away"

        presenter.getDetailTeam(idAwayTeam, side)

        argumentCaptor<TeamListRepositoryCallback>().apply {
            verify(repository).getDetailAwayTeam(eq(idAwayTeam), capture())
            firstValue.onDataLoaded(awayTeams, side)
        }

        Mockito.verify(view).onDataLoaded(awayTeams, side)
    }

    @Test
    fun getDetailAwayTeamErrorTest() {
        val idAwayTeam = ""
        val side = "away"

        presenter.getDetailTeam(idAwayTeam, side)

        argumentCaptor<TeamListRepositoryCallback>().apply {
            verify(repository).getDetailAwayTeam(eq(idAwayTeam), capture())
            firstValue.onDataError(side)
        }

        Mockito.verify(view).onDataError(side)
    }
}