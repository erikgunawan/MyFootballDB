package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: LeagueRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = LeagueRepositoryImpl(apiService)
    }

    @Test
    fun getAllLeaguesTest() {
        repository.getAllLeagues()
        Mockito.verify(apiService).getAllLeagues()
    }
}