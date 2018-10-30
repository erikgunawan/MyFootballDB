package id.ergun.myfootballdb.modules.favorite.match

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.ProgressBar
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.R.color.colorAccent
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.models.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFavoriteBaseUI<T> : AnkoComponent<T> {

    val eventList: MutableList<Event> = mutableListOf()

    lateinit var rvEvent: RecyclerView
    lateinit var adapter: BaseMatchAdapter
    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<T>) = with(ui) {

        verticalLayout {
            lparams (width = matchParent, height = matchParent)

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams (width = matchParent, height = matchParent)

                    rvEvent = recyclerView {
                        id = R.id.rv_event
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(width = matchParent, height = matchParent)

                    progressBar = progressBar {
                    }.lparams {
                        centerInParent()
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
    }
}