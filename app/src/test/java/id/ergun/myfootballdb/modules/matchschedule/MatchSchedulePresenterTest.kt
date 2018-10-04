package id.ergun.myfootballdb.modules.matchschedule

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.repositories.EventListRepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchSchedulePresenterTest {

    @Mock
    private lateinit var view: MatchScheduleView

    @Mock
    private lateinit var repository: MatchScheduleRepository

    @Mock
    private lateinit var events: DTOEventList

    private lateinit var presenter: MatchSchedulePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchSchedulePresenter(view, repository)
    }

    @Test
    fun getEventsNextLeagueTest() {
        val id = "4328"

        presenter.getEventsNextLeague(id)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getEventsNextLeague(eq(id), capture())
            firstValue.onDataLoaded(events)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(events)
    }

    @Test
    fun getEventsNextLeagueErrorTest() {
        val id = ""

        presenter.getEventsNextLeague(id)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getEventsNextLeague(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataError()
    }

    @Test
    fun getEventsPastLeagueTest() {
        val id = "4328"

        presenter.getEventsPastLeague(id)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getEventsPastLeague(eq(id), capture())
            firstValue.onDataLoaded(events)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(events)
    }

    @Test
    fun getEventsPastLeagueErrorTest() {
        val id = ""

        presenter.getEventsPastLeague(id)

        argumentCaptor<EventListRepositoryCallback>().apply {
            verify(repository).getEventsPastLeague(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataError()
    }
}