package id.ergun.myfootballdb.bases.ui.match

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import id.ergun.myfootballdb.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class BaseMatchAdapterUI : AnkoComponent<ViewGroup> {

    lateinit var ivAddToCalendar: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        cardView {
            lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }

            backgroundColor = Color.GRAY

            relativeLayout {
                padding = dip(8)
                lparams(width = matchParent, height = wrapContent)
                backgroundColor = Color.WHITE

                ivAddToCalendar = imageView {
                    id = R.id.iv_add_to_calendar
                    backgroundResource = R.drawable.ic_add_to_calendar_24dp
                }.lparams(width = dip(25), height = dip(25)) {
                    alignParentRight()
                    alignParentTop()
                }

                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)

                    textView {
                        id = R.id.tv_date_event
                        gravity = Gravity.CENTER_HORIZONTAL
                        textSize = 14F
                        textColor = Color.BLUE
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = matchParent, height = wrapContent)

                    textView {
                        id = R.id.tv_time_event
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
}