package id.ergun.myfootballdb.modules.match.schedule.next

import android.Manifest
import android.R.layout.simple_spinner_dropdown_item
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.bases.adapters.SpinnerLeagueAdapter
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.models.League
import id.ergun.myfootballdb.modules.match.schedule.MatchScheduleContract
import id.ergun.myfootballdb.modules.match.schedule.MatchSchedulePresenter
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailActivity
import id.ergun.myfootballdb.utils.getTimeInMillis
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class MatchScheduleNextFragment: Fragment(), MatchScheduleContract.View,
        BaseMatchAdapter.ItemClickListener, BaseMatchAdapter.AddToCalendarClickListener {

    private lateinit var presenter: MatchSchedulePresenter

    private lateinit var view: MatchScheduleNextUI<MatchScheduleNextFragment>

    private var leagues: MutableList<League> = mutableListOf()

    private lateinit var adapterLeague: SpinnerLeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = MatchScheduleNextUI()
        val rootView = view.createView(AnkoContext.create(ctx, this))

        presenter = MatchSchedulePresenter(this)
        presenter.onAttach(this)
        presenter.getAllLeagues()

        adapterLeague = SpinnerLeagueAdapter(ctx, simple_spinner_dropdown_item, leagues)
        view.spinLeague.adapter = adapterLeague
        view.spinLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.getEventsNextLeague(adapterLeague.getItem(position).idLeague.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        calendarPermission()
        view.adapter = BaseMatchAdapter(view.eventList, this, this, true)
        view.rvEvent.adapter = view.adapter
        return rootView
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<MatchScheduleDetailActivity>(EVENT to view.adapter.getData()[position])
    }

    private fun calendarPermission() = runWithPermissions(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR) {
        Log.d("CALENDAR_PERMISSION", "Permission granted")
    }

    override fun onAddToCalendarClick(v: View, position: Int) {

        val event = view.adapter.getData()[position]

        val startDateTime = event.dateEvent + " " + event.strTime

        val startDateTimeInMillis = getTimeInMillis(startDateTime)
        val endDateTimeInMillis = startDateTimeInMillis + (90 * 60 * 1000)
        val alarmTimeInMillis = startDateTimeInMillis - (30 * 60 * 1000)

        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra(CalendarContract.Events.CALENDAR_ID, event.idEvent)
        intent.putExtra(CalendarContract.Events.TITLE, event.strEvent)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDateTimeInMillis)
        intent.putExtra(CalendarContract.Events.ALL_DAY, false)
        intent.putExtra(CalendarContract.Events.HAS_ALARM, 1)
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=ONCE")
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDateTimeInMillis)
        intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, alarmTimeInMillis)
        startActivity(intent)
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        leagues.clear()
        adapterLeague.addAll(data)
        adapterLeague.notifyDataSetChanged()

        if (data.isNotEmpty())
            presenter.getEventsNextLeague(adapterLeague.getItem(0).idLeague.toString())
    }

    override fun resetLeagueList() {
        leagues.clear()
        adapterLeague.notifyDataSetChanged()
    }

    override fun resetDataList() {
        view.eventList.clear()
        view.adapter.notifyDataSetChanged()
    }

    override fun showDataList(data: List<Event>) {
        view.eventList.clear()
        view.eventList.addAll(data)
        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}