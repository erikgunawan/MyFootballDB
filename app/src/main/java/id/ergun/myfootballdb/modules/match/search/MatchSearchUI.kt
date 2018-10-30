package id.ergun.myfootballdb.modules.match.search

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ProgressBar
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.models.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchSearchUI : AnkoComponent<MatchSearchActivity> {

    val eventList: MutableList<Event> = mutableListOf()

    lateinit var rvEvent: RecyclerView
    lateinit var adapter: BaseMatchAdapter

    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<MatchSearchActivity>) = with(ui) {

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                rvEvent = recyclerView {
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