package id.ergun.myfootballdb.modules.matchschedule

import android.graphics.Color
import android.support.design.widget.BottomNavigationView
import android.view.Gravity
import android.widget.FrameLayout
import id.ergun.myfootballdb.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView


class MatchScheduleUI : AnkoComponent<MatchScheduleActivity> {
    private lateinit var layoutContent: FrameLayout
    lateinit var navigationView: BottomNavigationView

    override fun createView(ui: AnkoContext<MatchScheduleActivity>) = with(ui) {

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            layoutContent = frameLayout {
                id = R.id.layout_content
            }.lparams(width = matchParent, height = 0, weight = 1F)

            view {
                backgroundColor = Color.GRAY
            }.lparams(width = matchParent, height = dip(1))

            navigationView = bottomNavigationView {
                id = R.id.navigation_match_schedule
                inflateMenu(R.menu.menu_nav_match_schedule)
            }.lparams(width = matchParent, height = wrapContent) {
                gravity = Gravity.BOTTOM
            }
        }
    }
}