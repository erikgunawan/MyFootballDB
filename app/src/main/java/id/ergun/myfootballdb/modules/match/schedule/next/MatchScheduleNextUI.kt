package id.ergun.myfootballdb.modules.match.schedule.next

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import id.ergun.myfootballdb.R.id.rv_event_next
import id.ergun.myfootballdb.R.id.spin_league
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.models.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchScheduleNextUI<T> : AnkoComponent<T> {

    val eventList: MutableList<Event> = mutableListOf()

    lateinit var rvEvent: RecyclerView
    lateinit var adapter: BaseMatchAdapter

    lateinit var spinLeague: Spinner
    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<T>) = with(ui) {

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinLeague = spinner {
                id = spin_league
            }

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                rvEvent = recyclerView {
                    id = rv_event_next
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams{
                    centerHorizontally()
                }
            }

        }
    }
}