package id.ergun.myfootballdb.modules.team.overview

import android.graphics.Color
import android.widget.TextView
import id.ergun.myfootballdb.R.id.tv_team_overview
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.support.v4.nestedScrollView

class TeamOverviewUI<T> : AnkoComponent<T> {

    lateinit var tvTeamOverview: TextView

    override fun createView(ui: AnkoContext<T>) = with(ui) {
        nestedScrollView {
            lparams(width = matchParent, height = matchParent)

            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }

                backgroundColor = Color.GRAY

                linearLayout {
                    padding = dip(8)
                    lparams(width = matchParent, height = wrapContent)
                    backgroundColor = Color.WHITE

                    tvTeamOverview = textView {
                        id = tv_team_overview
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }
}