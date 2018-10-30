package id.ergun.myfootballdb.bases.ui.match

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.R.id.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class BasePlayerAdapterUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }

                backgroundColor = Color.GRAY

                relativeLayout {
                    padding = dip(8)
                    lparams(width = matchParent, height = wrapContent)
                    backgroundColor = Color.WHITE

                    imageView {
                        id = iv_player_cutout
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = tv_player_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(8)
                        rightOf(iv_player_cutout)
                        leftOf(tv_player_position)
                        centerVertically()
                    }

                    textView {
                        id = tv_player_position
                        textSize = 14f
                    }.lparams {
                        margin = dip(8)
                        alignParentRight()
                        centerVertically()
                    }
                }
            }
        }
    }

}