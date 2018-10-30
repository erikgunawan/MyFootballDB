package id.ergun.myfootballdb.modules.team

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import id.ergun.myfootballdb.R.id.rv_team
import id.ergun.myfootballdb.bases.adapters.BaseTeamAdapter
import id.ergun.myfootballdb.models.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamUI<T> : AnkoComponent<T> {

    val teamList: MutableList<Team> = mutableListOf()

    lateinit var rvTeam: RecyclerView
    lateinit var adapter: BaseTeamAdapter

    lateinit var spinLeague: Spinner
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<T>) = with(ui) {

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinLeague = spinner ()

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                rvTeam = recyclerView {
                    id = rv_team
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