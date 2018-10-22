package id.ergun.myfootballdb.modules.matchschedule.detail

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchScheduleDetailRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: MatchScheduleDetailRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MatchScheduleDetailRepositoryImpl(apiService)
    }

    @Test
    fun getDetailEventTest() {
        val id = "4328"
        repository.getDetailEvent(id)
        Mockito.verify(apiService).getDetailEvent(id)
    }

    @Test
    fun getDetailTeamTest() {
        val id = "4328"
        repository.getDetailTeam(id)
        Mockito.verify(apiService).getDetailTeam(id)
    }
}