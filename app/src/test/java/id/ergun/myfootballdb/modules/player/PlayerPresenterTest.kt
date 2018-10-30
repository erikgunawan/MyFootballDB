package id.ergun.myfootballdb.modules.player

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOPlayerList
import id.ergun.myfootballdb.models.Player
import id.ergun.myfootballdb.repositories.PlayerRepositoryImpl
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

class PlayerPresenterTest {

    @Mock
    private lateinit var view: PlayerContract.View

    @Mock
    private lateinit var playerRepository: PlayerRepositoryImpl

    private lateinit var dtoPlayerList: DTOPlayerList

    private var players = mutableListOf<Player>()

    private lateinit var presenter: PlayerPresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dtoPlayerList = DTOPlayerList(players)

        presenter = PlayerPresenter(
                view = view,
                playerRepository = playerRepository,
                schedulerProvider = ScheduleProviderTest(),
                compositeDisposable = compositeDisposable
        )
    }

    @Test
    fun getAllPlayersTest() {
        val id = "133604"
        whenever(playerRepository.getAllPlayers(id))
                .thenReturn(Observable.just(dtoPlayerList))

        presenter.getAllPlayers(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(dtoPlayerList)
        Mockito.verify(view).hideLoading()
    }
}