package id.ergun.myfootballdb.modules.matchschedule.fragments

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.R.color.colorAccent
import id.ergun.myfootballdb.modules.matchschedule.Event
import id.ergun.myfootballdb.modules.matchschedule.MatchScheduleAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchScheduleBaseUI<T> : AnkoComponent<T> {

    val eventList: MutableList<Event> = mutableListOf()

    lateinit var rvEvent: RecyclerView
    lateinit var adapter: MatchScheduleAdapter
    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<T>) = with(ui) {

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                        gravity = Gravity.CENTER
                    }

                    rvEvent = recyclerView {
                        id = R.id.rv_event
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = wrapContent) {
                        above(R.id.layout_menu)
                    }
                }
            }
        }
    }
}