package id.ergun.myfootballdb.modules.matchschedule

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class MatchScheduleRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: MatchScheduleRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MatchScheduleRepositoryImpl(apiService)
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
}