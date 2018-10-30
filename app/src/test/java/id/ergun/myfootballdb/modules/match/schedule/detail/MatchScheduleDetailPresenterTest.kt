package id.ergun.myfootballdb.modules.matchschedule.detail

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOEventList
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailContract
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailPresenter
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
import id.ergun.myfootballdb.repositories.TeamRepositoryImpl
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

class MatchScheduleDetailPresenterTest {

    @Mock
    private lateinit var view: MatchScheduleDetailContract.View

    @Mock
    private lateinit var eventRepository: EventRepositoryImpl

    @Mock
    private lateinit var teamRepository: TeamRepositoryImpl

    private lateinit var dtoEventList: DTOEventList
    private lateinit var dtoTeamList: DTOTeamList

    private lateinit var event: Event

    private var events = mutableListOf<Event>()

    private lateinit var team: Team
    private var teams = mutableListOf<Team>()

    private lateinit var presenter: MatchScheduleDetailPresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        event = Event(idEvent = 576548,
                idSoccerXML = 389933,
                strEvent = "Fulham vs Arsenal",
                strFilename = "English Premier League 2018-10-06 Fulham vs Arsenal",
                strSport = "Soccer",
                idLeague = 4328,
                strLeague = "English Premier League",
                strSeason = "1819",
                strHomeTeam = "Fulham",
                strAwayTeam = "Arsenal",
                intHomeScore = 1,
                intRound = 8,
                intAwayScore = 5,
                strHomeGoalDetails = "44':Andre Schuerrle;",
                strHomeRedCards = "",
                strHomeYellowCards = "57':Luciano Dario Vietto;70':Andre Schuerrle;",
                strHomeLineupGoalkeeper = "Marcus Bettinelli; ",
                strHomeLineupDefense = "Denis Odoi; Tim Ream; Alfie Mawson; Maxime Le Marchand; ",
                strHomeLineupMidfield = "Tom Cairney; Jean Michael Seri; Andre Schuerrle; Luciano Dario Vietto; Ryan Sessegnon; ",
                strHomeLineupForward = "Aleksandar Mitrovic; ",
                strHomeLineupSubstitutes = "Sergio Rico; Alfie Mawson; Kevin McDonald; Stefan Johansen; Steven Sessegnon; Floyd Ayite; Aboubakar Kamara; ",
                strHomeFormation = "",
                strAwayRedCards = "",
                strAwayYellowCards = "",
                strAwayGoalDetails = "29':Alexandre Lacazette;49':Alexandre Lacazette;68':Aaron Ramsey;79':Pierre-Emerick Aubameyang;90':Pierre-Emerick Aubameyang;",
                strAwayLineupGoalkeeper = "Bernd Leno; ",
                strAwayLineupDefense = "Hector Bellerin; Shkodran Mustafi; Sokratis Papastathopoulos; Nacho Monreal; ",
                strAwayLineupMidfield = "Lucas Torreira; Granit Xhaka; Mesut Oezil; Aaron Ramsey; Pierre-Emerick Aubameyang; ",
                strAwayLineupForward = "Alexandre Lacazette; ",
                strAwayLineupSubstitutes = "Emiliano Martinez; Sokratis Papastathopoulos; Stephan Lichtsteiner; Sead Kolasinac; Aaron Ramsey; Matteo Guendouzi; Pierre-Emerick Aubameyang; ",
                strAwayFormation = "",
                intHomeShots = 4,
                intAwayShots = 7,
                dateEvent = "2018-10-07",
                strDate = "07/10/18",
                strTime = "11:00:00+00:00",
                idHomeTeam = 133600,
                idAwayTeam = 133604,
                strLocked = "unlocked"
        )

        events.add(event)
        dtoEventList = DTOEventList(events)

        team = Team(
                idTeam = 133600,
                idSoccerXML = 4,
                intLoved = null,
                strTeam = "Fulham",
                strTeamShort = null,
                strAlternate = "",
                intFormedYear = 1879,
                strSport = "Soccer",
                strLeague = "English Premier League",
                idLeague = 4328,
                strManager = "Slaviša Jokanović",
                strStadium = "Craven Cottage",
                strKeywords = "",
                strRSS = "",
                strStadiumThumb = "https://www.thesportsdb.com/images/media/team/stadium/uxywyw1420751718.jpg",
                strStadiumDescription = "Craven Cottage is the name of a football stadium located in Fulham, London. It has been the home ground of Fulham F.C. since 1896. The ground's current capacity is 25,700, all-seated, though the record attendance is 49,335, for a game against Millwall Dockers, 8 October 1938. Located next to Bishop's Park on the banks of the River Thames, 'Craven Cottage' was originally a royal hunting lodge and has history dating back over 300 years.\r\n\r\nAs well as by Fulham, the stadium has been also been used by the United States men's national soccer team, Australia national soccer team, the Republic of Ireland national football team, and Canada men's national soccer team, and was formerly the home ground for rugby league team Fulham RLFC.",
                strStadiumLocation = "Fulham, London",
                intStadiumCapacity = 25700,
                strWebsite = "www.fulhamfc.com",
                strFacebook = "www.facebook.com/FulhamFC",
                strTwitter = "twitter.com/FulhamFC",
                strInstagram = "www.instagram.com/fulhamfc",
                strDescriptionEN = "Fulham Football Club is a professional association football club based in Fulham, Greater London, England. Founded in 1879, they play in the Championship, having been relegated from the Premier League in 2013–14 after 13 consecutive seasons in the top-flight. They are the oldest-established football team from London to have played in the Premier League. After selling their top striker, Ross McCormack, and allowing Moussa Dembele to go on a free to Celtic, all hope of promotion back to the heights of the premier league were lost. Recently, they lost 2-1 to their rivals QPR (Queens Park Rangers) at home, the first time in 36 years that had happened. \r\n\r\nThe club has spent 25 seasons in English football's top division, the majority of these in two spells during the 1960s and 2000s. The latter spell was associated with former chairman Mohamed Al-Fayed, after the club had climbed up from the fourth tier in the 1990s. Fulham have never won a major honour, although they have reached two major finals: in 1975, as a Second Division team, they contested the FA Cup Final for the only time in their history, losing 2–0 to West Ham United, and in 2010 they reached the final of the UEFA Europa League, which they contested with Atlético Madrid in Hamburg, losing 2–1 after extra time.\r\n\r\nThe club has produced many English greats, including Johnny Haynes, George Cohen, Bobby Robson, Rodney Marsh and Alan Mullery. They play at Craven Cottage, a ground on the banks of the River Thames in Fulham which has been their home since 1896. Fulham's training ground is located near Motspur Park, where the club's Academy is also situated.",
                strDescriptionDE = null,
                strDescriptionIT = null,
                strGender = "Male",
                strCountry = "England",
                strTeamBadge = "https://www.thesportsdb.com/images/media/team/badge/xwwvyt1448811086.png",
                strTeamJersey = "https://www.thesportsdb.com/images/media/team/jersey/vrttsr1454114628.png",
                strTeamLogo = "https://www.thesportsdb.com/images/media/team/logo/ey93jj1509012596.png",
                strTeamFanart1 = "https://www.thesportsdb.com/images/media/team/fanart/jv19st1532296431.jpg",
                strTeamFanart2 = "https://www.thesportsdb.com/images/media/team/fanart/vtj30z1532296500.jpg",
                strTeamFanart3 = "https://www.thesportsdb.com/images/media/team/fanart/o4lhkf1532296527.jpg",
                strTeamFanart4 = "https://www.thesportsdb.com/images/media/team/fanart/flhg8e1532296446.jpg",
                strTeamBanner = "https://www.thesportsdb.com/images/media/team/banner/cjl9kv1532296720.jpg",
                strYoutube = "www.youtube.com/fulhamfc",
                strLocked = "unlocked"
        )
        teams.add(team)

        dtoTeamList = DTOTeamList(teams)

        presenter = MatchScheduleDetailPresenter(
                view = view,
                eventRepository = eventRepository,
                teamRepository = teamRepository,
                schedulerProvider = ScheduleProviderTest(),
                compositeDisposable = compositeDisposable
        )
    }

    @Test
    fun getDetailEventTest() {
        val id = "4328"

        whenever(eventRepository.getDetailEvent(id))
                .thenReturn(Observable.just(dtoEventList))

        presenter.getDetailEvent(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showData(event)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getDetailTeamTest() {
        val id = "4328"

        whenever(teamRepository.getDetailTeam(id))
                .thenReturn(Observable.just(dtoTeamList))

        presenter.getDetailHomeTeam(id)
        Mockito.verify(view).showHomeBadge(team.strTeamBadge)
    }
}