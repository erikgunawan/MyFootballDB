package id.ergun.myfootballdb.modules.matchschedule.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.R.drawable.ic_add_favorite
import id.ergun.myfootballdb.R.drawable.ic_added_favorite
import id.ergun.myfootballdb.R.id.add_to_favorite
import id.ergun.myfootballdb.R.menu.menu_match_schedule_detail
import id.ergun.myfootballdb.bases.models.DTOTeamList
import id.ergun.myfootballdb.configs.AWAY
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.configs.HOME
import id.ergun.myfootballdb.db.MatchFavorite
import id.ergun.myfootballdb.db.database
import id.ergun.myfootballdb.models.Team
import id.ergun.myfootballdb.modules.matchschedule.Event
import id.ergun.myfootballdb.utils.formatString
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.toLocalDate
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class MatchScheduleDetailActivity : AppCompatActivity(), MatchScheduleDetailView {

    private lateinit var presenter: MatchScheduleDetailPresenter
    private lateinit var view: MatchScheduleDetailUI

    private lateinit var event: Event

    private var homeBadge: String? = null
    private var awayBadge: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MatchScheduleDetailUI()
        view.setContentView(this)

        event = intent.getParcelableExtra(EVENT)

        fillData(event)

        presenter = MatchScheduleDetailPresenter(this)
        presenter.onAttach(this)
        loadAll()

        favoriteState()

        view.swipeRefresh.onRefresh {
            view.swipeRefresh.isRefreshing = false
            loadAll()
        }
    }

    private fun loadAll() {
        presenter.getDetailEvent(event.idEvent.toString())
        presenter.getDetailHomeTeam(event.idHomeTeam.toString())
        presenter.getDetailAwayTeam(event.idAwayTeam.toString())
//        presenter.getDetailTeam(event.idHomeTeam.toString(), HOME)
//        presenter.getDetailTeam(event.idAwayTeam.toString(), AWAY)
    }

    private fun fillData(event: Event) {
        this.event = event

        view.tvDateEvent.text = toLocalDate(event.dateEvent)

        view.tvHomeTeam.text = event.strHomeTeam

        val homeScore = event.intHomeScore ?: ""
        view.tvHomeScore.text = homeScore.toString()

        view.tvHomeFormation.text = event.strHomeFormation
        view.tvHomeGoalDetails.text = formatString(event.strHomeGoalDetails,";",";\n")

        val homeShots = event.intHomeShots ?: ""
        view.tvHomeShots.text = homeShots.toString()
        view.tvHomeLineupGoalkeeper.text = formatString(event.strHomeLineupGoalkeeper,";",";\n")
        view.tvHomeLineupDefense.text = formatString(event.strHomeLineupDefense,";",";\n")
        view.tvHomeLineupMidfielder.text = formatString(event.strHomeLineupMidfield,";",";\n")
        view.tvHomeLineupForward.text = formatString(event.strHomeLineupForward,";",";\n")
        view.tvHomeLineupSubtitutes.text = formatString(event.strHomeLineupSubstitutes,";",";\n")

        view.tvAwayTeam.text = event.strAwayTeam

        val awayScore = event.intAwayScore ?: ""
        view.tvAwayScore.text = awayScore.toString()

        view.tvAwayFormation.text = event.strAwayFormation
        view.tvAwayGoalDetails.text = formatString(event.strAwayGoalDetails,";",";\n")

        val awayShots = event.intAwayShots ?: ""
        view.tvAwayShots.text = awayShots.toString()

        view.tvAwayLineupGoalkeeper.text = formatString(event.strAwayLineupGoalkeeper,";",";\n")
        view.tvAwayLineupDefense.text = formatString(event.strAwayLineupDefense,";",";\n")
        view.tvAwayLineupMidfielder.text = formatString(event.strAwayLineupMidfield,";",";\n")
        view.tvAwayLineupForward.text = formatString(event.strAwayLineupForward,";",";\n")

        view.tvAwayLineupSubtitutes.text = formatString(event.strAwayLineupSubstitutes,";",";\n")
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun showData(event: Event) {
        fillData(event)
    }

    override fun showHomeBadge(url: String?) {
        this.homeBadge = url
        Glide.with(this).load(url).into(view.ivHomeBadge)
    }

    override fun showAwayBadge(url: String?) {
        this.awayBadge = url
        Glide.with(this).load(url).into(view.ivAwayBadge)
    }

//    override fun onDataError() {
//        hideLoading()
//    }

//    override fun onDataLoaded(data: DTOEventList) {
//        hideLoading()
//
//        val ev: Event = data.data[0]
//        showData(ev)
//    }

    override fun onDataLoaded(data: DTOTeamList, side: String) {
        val team: Team = data.data[0]
        if (side == HOME) {
            showHomeBadge(team.strTeamBadge)
        }
        else if (side == AWAY) {
            showAwayBadge(team.strTeamBadge)
        }
    }

    override fun onDataError(side: String) {
        Log.d("error", side)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_match_schedule_detail, menu)
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

            add_to_favorite -> {
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
                insert(MatchFavorite.TABLE_MATCH_FAVORITE,
                        MatchFavorite.ID_EVENT to event.idEvent,
                        MatchFavorite.DATE_EVENT to event.dateEvent,
                        MatchFavorite.ID_HOME_TEAM to event.idHomeTeam,
                        MatchFavorite.HOME_TEAM to event.strHomeTeam,
                        MatchFavorite.HOME_SCORE to event.intHomeScore,
                        MatchFavorite.HOME_FORMATION to event.strHomeFormation,
                        MatchFavorite.HOME_GOAL_DETAILS to event.strHomeGoalDetails,
                        MatchFavorite.HOME_SHOTS to event.intHomeShots,
                        MatchFavorite.HOME_LINEUP_GOALKEEPER to event.strHomeLineupGoalkeeper,
                        MatchFavorite.HOME_LINEUP_DEFENSE to event.strHomeLineupDefense,
                        MatchFavorite.HOME_LINEUP_MIDFIELDER to event.strHomeLineupMidfield,
                        MatchFavorite.HOME_LINEUP_FORWARD to event.strHomeLineupForward,
                        MatchFavorite.HOME_LINEUP_SUBSTITUTES to event.strHomeLineupSubstitutes,
                        MatchFavorite.HOME_BADGE to homeBadge,
                        MatchFavorite.ID_AWAY_TEAM to event.idAwayTeam,
                        MatchFavorite.AWAY_TEAM to event.strAwayTeam,
                        MatchFavorite.AWAY_SCORE to event.intAwayScore,
                        MatchFavorite.AWAY_FORMATION to event.strAwayFormation,
                        MatchFavorite.AWAY_GOAL_DETAILS to event.strAwayGoalDetails,
                        MatchFavorite.AWAY_SHOTS to event.intAwayShots,
                        MatchFavorite.AWAY_LINEUP_GOALKEEPER to event.strAwayLineupGoalkeeper,
                        MatchFavorite.AWAY_LINEUP_DEFENSE to event.strAwayLineupDefense,
                        MatchFavorite.AWAY_LINEUP_MIDFIELDER to event.strAwayLineupMidfield,
                        MatchFavorite.AWAY_LINEUP_FORWARD to event.strAwayLineupForward,
                        MatchFavorite.AWAY_LINEUP_SUBSTITUTES to event.strAwayLineupSubstitutes,
                        MatchFavorite.AWAY_BADGE to awayBadge
                )
            }
            snackbar(view.swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view.swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(MatchFavorite.TABLE_MATCH_FAVORITE, "(" + MatchFavorite.ID_EVENT + " = {id})",
                        "id" to event.idEvent.toString())
            }
            snackbar(view.swipeRefresh, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view.swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
    }

    private fun favoriteState(){
        database.use {
            val result = select(MatchFavorite.TABLE_MATCH_FAVORITE)
                    .whereArgs("(" + MatchFavorite.ID_EVENT + " = {id})",
                            "id" to event.idEvent.toString())
            val favorite = result.parseList(classParser<MatchFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}