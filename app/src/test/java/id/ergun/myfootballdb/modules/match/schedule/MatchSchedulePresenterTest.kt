package id.ergun.myfootballdb.modules.matchschedule

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOLeagueList
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.modules.match.schedule.MatchScheduleContract
import id.ergun.myfootballdb.modules.match.schedule.MatchSchedulePresenter
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
import id.ergun.myfootballdb.repositories.LeagueRepositoryImpl
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

class MatchSchedulePresenterTest {

    @Mock
    private lateinit var view: MatchScheduleContract.View

    @Mock
    private lateinit var leagueRepository: LeagueRepositoryImpl

    @Mock
    private lateinit var eventRepository: EventRepositoryImpl

    private lateinit var dtoLeagueList: DTOLeagueList

    private var leagues = mutableListOf<League>()

    private lateinit var dtoEventList: DTOEventList

    private var events = mutableListOf<Event>()

    private lateinit var presenter: MatchSchedulePresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dtoLeagueList = DTOLeagueList(leagues)

        dtoEventList = DTOEventList(events)

        presenter = MatchSchedulePresenter(
                view = view,
                leagueRepository = leagueRepository,
                eventRepository = eventRepository,
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
    fun getEventsNextLeagueTest() {
        val id = "4328"

        whenever(eventRepository.getEventsNextLeague(id))
                .thenReturn(Observable.just(dtoEventList))

        presenter.getEventsNextLeague(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(events)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getEventsPastLeagueTest() {
        val id = "4328"

        whenever(eventRepository.getEventsPastLeague(id))
                .thenReturn(Observable.just(dtoEventList))

        presenter.getEventsPastLeague(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(events)
        Mockito.verify(view).hideLoading()
    }
}