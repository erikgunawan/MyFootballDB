package id.ergun.myfootballdb.modules.matchschedule

import com.nhaarman.mockito_kotlin.whenever
import id.ergun.myfootballdb.bases.models.DTOEventList
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

class MatchSchedulePresenterTest {

    @Mock
    private lateinit var view: MatchScheduleView

    @Mock
    private lateinit var repository: MatchScheduleRepositoryImpl

    private lateinit var dtoEventList: DTOEventList

    private var events = mutableListOf<Event>()

    private lateinit var presenter: MatchSchedulePresenter

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        dtoEventList = DTOEventList(events)

        presenter = MatchSchedulePresenter(view, repository, ScheduleProviderTest(), compositeDisposable)
    }

    @Test
    fun getEventsNextLeagueTest() {
        val id = "4328"

        whenever(repository.getEventsNextLeague(id))
                .thenReturn(Observable.just(dtoEventList))

        presenter.getEventsNextLeague(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(dtoEventList)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getEventsPastLeagueTest() {
        val id = "4328"

        whenever(repository.getEventsPastLeague(id))
                .thenReturn(Observable.just(dtoEventList))

        presenter.getEventsPastLeague(id)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDataList(dtoEventList)
        Mockito.verify(view).hideLoading()
    }
}