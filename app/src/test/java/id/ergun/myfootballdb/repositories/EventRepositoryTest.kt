package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: EventRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = EventRepositoryImpl(apiService)
    }

    @Test
    fun getEventsNextLeagueTest() {
        val id = "4328"
        repository.getEventsNextLeague(id)
        Mockito.verify(apiService).getEventsNextLeague(id)
    }

    @Test
    fun getEventsPastLeagueTest() {
        val id = "4328"
        repository.getEventsPastLeague(id)
        Mockito.verify(apiService).getEventsPastLeague(id)
    }

    @Test
    fun getDetailEventTest() {
        val id = "4328"
        repository.getDetailEvent(id)
        Mockito.verify(apiService).getDetailEvent(id)
    }

    @Test
    fun searchEventsTest() {
        val name = "Arsenal"
        repository.searchEvents(name)
        Mockito.verify(apiService).searchEvents(name)
    }
}