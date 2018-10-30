package id.ergun.myfootballdb.modules.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.modules.favorite.FavoriteFragment
import id.ergun.myfootballdb.modules.match.schedule.MatchScheduleFragment
import id.ergun.myfootballdb.modules.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigation_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = MatchScheduleFragment()
        addFragment(fragment)
    }

    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
//                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.layout_content, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                val fragment = MatchScheduleFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_team -> {
                val fragment = TeamFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}