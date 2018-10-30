package id.ergun.myfootballdb.modules.team.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.bases.adapters.BaseViewPagerAdapter
import id.ergun.myfootballdb.configs.TEAM
import id.ergun.myfootballdb.db.TeamFavorite
import id.ergun.myfootballdb.db.database
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.player.PlayerFragment
import id.ergun.myfootballdb.modules.team.overview.TeamOverviewFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity: AppCompatActivity(), TeamDetailContract.View {

    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        team = intent.getParcelableExtra(TEAM)

        val adapter = BaseViewPagerAdapter(supportFragmentManager)
//        setHasOptionsMenu(true)

        val bundle = bundleOf(TEAM to team)

        val teamOverviewFragment = TeamOverviewFragment()
        teamOverviewFragment.arguments = bundle

        val teamPlayerFragment = PlayerFragment()
        teamPlayerFragment.arguments = bundle

        adapter.populateFragment(teamOverviewFragment, "Overview")
        adapter.populateFragment(teamPlayerFragment, "Players")
        viewpager_team.adapter = adapter
        tab_team.setupWithViewPager(viewpager_team)

        fillView(team)

        favoriteState()

    }

    private fun fillView(team: Team) {
        tv_team_name.text = team.strTeam
        tv_team_formed_year.text = team.intFormedYear.toString()
        tv_team_stadium.text = team.strStadium
        Glide.with(this).load(team.strTeamBadge).into(iv_team_badge)
    }

    override fun showData(data: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
//        view.progressBar.visible()
    }

    override fun hideLoading() {
//        view.progressBar.invisible()
    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TeamFavorite.TABLE_TEAM_FAVORITE,
                        TeamFavorite.ID_TEAM to team.idTeam,
                        TeamFavorite.NAME_TEAM to team.strTeam,
                        TeamFavorite.DESCRIPTION_TEAM to team.strDescriptionEN,
                        TeamFavorite.FORMED_YEAR_TEAM to team.intFormedYear,
                        TeamFavorite.STADIUM_TEAM to team.strStadium,
                        TeamFavorite.BADGE_TEAM to team.strTeamBadge
                )
            }
            snackbar(viewpager_team, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(viewpager_team, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TeamFavorite.TABLE_TEAM_FAVORITE, "(" + TeamFavorite.ID_TEAM + " = {id})",
                        "id" to team.idTeam.toString())
            }
            snackbar(viewpager_team, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(viewpager_team, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite_white_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite_white_24dp)
    }

    private fun favoriteState(){
        database.use {
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
                    .whereArgs("(" + TeamFavorite.ID_TEAM + " = {id})",
                            "id" to team.idTeam.toString())
            val favorite = result.parseList(classParser<TeamFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}