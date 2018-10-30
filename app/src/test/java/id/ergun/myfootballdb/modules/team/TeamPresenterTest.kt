package id.ergun.myfootballdb.modules.team

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOLeagueList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.repositories.LeagueRepositoryImpl
import id.ergun.myfootballdb.repositories.TeamRepositoryImpl
import id.ergun.myfootballdb.utils.scheduler.RxImmediateSchedulerRule
import id.ergun.myfootballdb.utils.scheduler.ScheduleProviderTest
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {

    @Mock
    private lateinit var view: TeamContract.View

    @Mock
    private lateinit var leagueRepository: LeagueRepositoryImpl

    @Mock
    private lateinit var teamRepository: TeamRepositoryImpl

    private lateinit var dtoLeagueList: DTOLeagueList

    private var leagues = mutableListOf<League>()

    private lateinit var dtoTeamList: DTOTeamList

    private var teams = mutableListOf<Team>()

    private lateinit var presenter: TeamPresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dtoLeagueList = DTOLeagueList(leagues)

        dtoTeamList = DTOTeamList(teams)

        presenter = TeamPresenter(
                view = view,
                leagueRepository = leagueRepository,
                teamRepository = teamRepository,
                schedulerProvider = ScheduleProviderTest(),
                compositeDisposable = compositeDisposable
        )
    }

    @Test
    fun getAllLeaguesTest() {
        whenever(leagueRepository.getAllLeagues())
                .thenReturn(Observable.just(dtoLeagueList))

        presenter.getAllLeagues()
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showLeagueList(leagues)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getAllTeamsByIdTest() {
        val id = "4328"
        whenever(teamRepository.getAllTeamsById(id))
                .thenReturn(Observable.just(dtoTeamList))

        presenter.getAllTeamsById(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getAllTeamsByNameTest() {
        val name = "English%20Premier%20League"
        whenever(teamRepository.getAllTeamsByName(name))
                .thenReturn(Observable.just(dtoTeamList))

        presenter.getAllTeamsByName(name)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun searchTeamsTest() {
        val name = "Arsenal"
        whenever(teamRepository.searchTeams(name))
                .thenReturn(Observable.just(dtoTeamList))

        presenter.searchTeams(name)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}