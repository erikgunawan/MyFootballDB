package id.ergun.myfootballdb.modules.matchschedule

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchScheduleAdapterUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        cardView {
            lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }

            backgroundColor = Color.GRAY

            verticalLayout {
                padding = dip(8)
                lparams(width = matchParent, height = wrapContent)
                backgroundColor = Color.WHITE

                textView {
                    id = R.id.tv_date_event
                    gravity = Gravity.CENTER_HORIZONTAL
                    textSize = 14F
                    textColor = Color.BLUE
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchParent, height = wrapContent)

                linearLayout {
                    padding = dip(8)
                    lparams(width = matchParent, height = wrapContent)

                    textView {
                        id = R.id.tv_home_team
                        gravity = Gravity.END
                        singleLine = true
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)

                    textView {
                        id = R.id.tv_home_score
                        gravity = Gravity.CENTER_HORIZONTAL
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = 0, height = wrapContent, weight = 0.1F)

                    textView("VS") {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = 0, height = wrapContent, weight = 0.1F)

                    textView {
                        id = R.id.tv_away_score
                        gravity = Gravity.CENTER_HORIZONTAL
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = 0, height = wrapContent, weight = 0.1F)

                    textView {
                        id = R.id.tv_away_team
                        gravity = Gravity.START
                        singleLine = true
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)
                }
            }
        }
    }
}