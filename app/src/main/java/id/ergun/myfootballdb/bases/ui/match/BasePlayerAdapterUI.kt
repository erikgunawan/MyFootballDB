package id.ergun.myfootballdb.bases.ui.match

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.R.id.iv_team_badge
import id.ergun.myfootballdb.R.id.tv_team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class BaseTeamAdapterUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }

                backgroundColor = Color.GRAY

                linearLayout {
                    padding = dip(8)
                    lparams(width = matchParent, height = wrapContent)
                    backgroundColor = Color.WHITE

                    imageView {
                        id = iv_team_badge
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = tv_team_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }

}