package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: PlayerRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = PlayerRepositoryImpl(apiService)
    }

    @Test
    fun getAllPlayersTest() {
        val id = "133600"
        repository.getAllPlayers(id)
        Mockito.verify(apiService).getAllPlayers(id)
    }

    @Test
    fun getDetailPlayerTest() {
        val id = "34145937"
        repository.getDetailPlayer(id)
        Mockito.verify(apiService).getDetailPlayer(id)
    }
}