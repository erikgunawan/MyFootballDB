package id.ergun.myfootballdb.modules.player.detail

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOPlayerDetailList
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


class PlayerDetailPresenterTest {

    @Mock
    private lateinit var view: PlayerDetailContract.View

    @Mock
    private lateinit var playerRepository: PlayerRepositoryImpl

    private lateinit var dtoPlayerDetailList: DTOPlayerDetailList

    private lateinit var player: Player

    private var players = mutableListOf<Player>()

    private lateinit var presenter: PlayerDetailPresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        player = Player(
                idPlayer = 34145937,
                idTeam = 133712,
                idPlayerManager = 43001238,
                strNationality = "Italy",
                strPlayer = "Mario Balotelli",
                strTeam = "Nice",
                strSport = "Soccer",
                intSoccerXMLTeamID = 6,
                dateBorn = "1990-08-12",
                dateSigned = "2014-08-25",
                strSigning = "£16,000,000",
                strWage = "£4,331,336",
                strBirthLocation = "Palermo, Italy",
                strDescriptionEN = "Mario Balotelli Barwuah (Italian pronunciation: ; born Mario Barwuah; 12 August 1990) is an Italian professional footballer who plays as a striker for Ligue 1 club Nice and the Italy national team.\r\n\r\nHe started his professional football career at Lumezzane and played for the first team twice before having an unsuccessful trial at Barcelona, and subsequently joining Internazionale in 2007. Inter manager Roberto Mancini brought Balotelli into the first team, but when Mancini left, Balotelli's disciplinary record fell away. He had a strained relationship with new manager José Mourinho and was suspended from Inter's first team in January 2009 after a number of disciplinary problems.\r\n\r\nWith doubts over his career at Inter, former coach Roberto Mancini had since moved to Manchester City and decided to give Balotelli a fresh chance at a new club. He joined Manchester City in August 2010, where his performances and off-field activities continued to be enigmatic and unpredictable. Balotelli eventually fell out of favour with Mancini after a \"training ground bust up\" between the two in January 2013. His departure from City and return to Italy with A.C. Milan followed several weeks later. After 18 months at Milan, he returned to the Premier League with Liverpool. An unsuccessful season with the Merseyside club led to his return to Milan on loan and subsequent departure on a free transfer to Nice.\r\n\r\nBalotelli earned his first cap for Italy in a friendly match against the Ivory Coast on 10 August 2010. He amassed over 30 caps and represented his country at UEFA Euro 2012, the 2013 FIFA Confederations Cup, and the 2014 FIFA World Cup; he helped the national side reach the final of Euro 2012, and also won a bronze medal at the Confederations Cup. Along with Antonio Cassano, he is Italy's top-scorer in the UEFA European Championships, with 3 goals. He is also Italy's top scorer in the FIFA Confederations Cup, alongside Giuseppe Rossi and Daniele De Rossi, with two goals.",
                strGender = "Male",
                strPosition = "Forward",
                strFacebook = "www.facebook.com/MarioOfficialBalotelli",
                strWebsite = "mariobalotelli.com",
                strTwitter = "twitter.com/finallymario",
                strInstagram = "instagram.com/mb459",
                strYoutube = "",
                strHeight = "1.88",
                strWeight = "86.17",
                intLoved = 0,
                strThumb = "https://www.thesportsdb.com/images/media/player/thumb/qqvuvq1431622719.jpg",
                strCutout = "https://www.thesportsdb.com/images/media/player/cutout/43001238.png",
                strFanart1 = "https://www.thesportsdb.com/images/media/player/fanart/wwtsqs1431622222.jpg",
                strFanart2 = "https://www.thesportsdb.com/images/media/player/fanart/upxsxy1431622235.jpg",
                strFanart3 = "https://www.thesportsdb.com/images/media/player/fanart/vxqsyv1431622253.jpg",
                strFanart4 = "https://www.thesportsdb.com/images/media/player/fanart/tvxuyq1431622269.jpg",
                strLocked = "unlocked"
        )
        players.add(player)

        dtoPlayerDetailList = DTOPlayerDetailList(players)

        presenter = PlayerDetailPresenter(
                view = view,
                playerRepository = playerRepository,
                schedulerProvider = ScheduleProviderTest(),
                compositeDisposable = compositeDisposable
        )
    }

    @Test
    fun getDetailPlayerTest() {
        val id = "34145937"
        whenever(playerRepository.getDetailPlayer(id))
                .thenReturn(Observable.just(dtoPlayerDetailList))

        presenter.getDetailPlayer(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showData(players[0])
        Mockito.verify(view).hideLoading()
    }
}