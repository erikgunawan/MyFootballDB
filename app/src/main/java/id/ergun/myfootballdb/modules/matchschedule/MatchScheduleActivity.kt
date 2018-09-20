package id.ergun.myfootballdb.modules.matchschedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.modules.matchschedule.fragments.MatchScheduleNextFragment
import id.ergun.myfootballdb.modules.matchschedule.fragments.MatchSchedulePrevFragment
import org.jetbrains.anko.setContentView

class MatchScheduleActivity : AppCompatActivity() {

    private lateinit var view: MatchScheduleUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MatchScheduleUI()
        view.setContentView(this)

        view.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = MatchSchedulePrevFragment()
        addFragment(fragment)
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.layout_content, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                val fragment = MatchSchedulePrevFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                val fragment = MatchScheduleNextFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}