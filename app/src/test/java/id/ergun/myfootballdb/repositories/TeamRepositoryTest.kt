package id.ergun.myfootballdb.repositories

import id.ergun.myfootballdb.configs.ApiService
import id.ergun.myfootballdb.configs.RetrofitClient
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamRepositoryTest {

    @Mock
    var apiService: ApiService = RetrofitClient().create()

    private lateinit var repository: TeamRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TeamRepositoryImpl(apiService)
    }

    @Test
    fun getAllTeamsByIdTest() {
        val id = "4328"
        repository.getAllTeamsById(id)
        Mockito.verify(apiService).getAllTeamsById(id)
    }

    @Test
    fun getAllTeamsByNameTest() {
        val name = "English%20Premier%20League"
        repository.getAllTeamsByName(name)
        Mockito.verify(apiService).getAllTeamsByName(name)
    }

    @Test
    fun getDetailTeamTest() {
        val id = "133600"
        repository.getDetailTeam(id)
        Mockito.verify(apiService).getDetailTeam(id)
    }

    @Test
    fun searchTeamsTest() {
        val name = "Arsenal"
        repository.searchTeams(name)
        Mockito.verify(apiService).searchTeams(name)
    }
}