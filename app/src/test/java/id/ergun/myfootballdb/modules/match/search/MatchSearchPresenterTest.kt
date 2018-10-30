package id.ergun.myfootballdb.modules.match.search

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOEventSearchList
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.repositories.EventRepositoryImpl
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

class MatchSearchPresenterTest {

    @Mock
    private lateinit var view: MatchSearchContract.View

    @Mock
    private lateinit var eventRepository: EventRepositoryImpl

    private lateinit var dtoEventSearchList: DTOEventSearchList

    private var events = mutableListOf<Event>()

    private lateinit var presenter: MatchSearchPresenter

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dtoEventSearchList = DTOEventSearchList(events)

        presenter = MatchSearchPresenter(
                view = view,
                eventRepository = eventRepository,
                schedulerProvider = ScheduleProviderTest(),
                compositeDisposable = compositeDisposable
        )
    }

    @Test
    fun getAllLeaguesTest() {
        val name = "Arsenal_vs_Chelsea"
        whenever(eventRepository.searchEvents(name))
                .thenReturn(Observable.just(dtoEventSearchList))

        presenter.searchEvents(name)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(events)
        Mockito.verify(view).hideLoading()
    }
}