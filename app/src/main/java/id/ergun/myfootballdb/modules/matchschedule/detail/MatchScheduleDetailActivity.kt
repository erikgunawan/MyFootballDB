package id.ergun.myfootballdb.modules.matchschedule.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.modules.matchschedule.Event
import id.ergun.myfootballdb.utils.formatString
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.toLocalDate
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.setContentView

class MatchScheduleDetailActivity : AppCompatActivity(), MatchScheduleDetailView {

    private lateinit var presenter: MatchScheduleDetailPresenter
    private lateinit var view: MatchScheduleDetailUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MatchScheduleDetailUI()
        view.setContentView(this)

        val event = intent.getParcelableExtra<Event>(EVENT)

        presenter = MatchScheduleDetailPresenter(this)
        presenter.onAttach(this)
        presenter.getDetailEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fillData(event: Event) {

        view.tvDateEvent.text = toLocalDate(event.dateEvent)

        view.tvHomeTeam.text = event.strHomeTeam

        val homescore = event.intHomeScore ?: ""
        view.tvHomeScore.text = homescore.toString()

        view.tvHomeFormation.text = event.strHomeFormation
        view.tvHomeGoalDetails.text = formatString(event.strHomeGoalDetails,";",";\n")

        val homeshots = event.intHomeShots ?: ""
        view.tvHomeShots.text = homeshots.toString()
        view.tvHomeLineupGoalkeeper.text = formatString(event.strHomeLineupGoalkeeper,";",";\n")
        view.tvHomeLineupDefense.text = formatString(event.strHomeLineupDefense,";",";\n")
        view.tvHomeLineupMidfielder.text = formatString(event.strHomeLineupMidfield,";",";\n")
        view.tvHomeLineupForward.text = formatString(event.strHomeLineupForward,";",";\n")
        view.tvHomeLineupSubtitutes.text = formatString(event.strHomeLineupSubstitutes,";",";\n")

        view.tvAwayTeam.text = event.strAwayTeam

        val awayscore = event.intAwayScore ?: ""
        view.tvAwayScore.text = awayscore.toString()

        view.tvAwayFormation.text = event.strAwayFormation
        view.tvAwayGoalDetails.text = formatString(event.strAwayGoalDetails,";",";\n")

        val awayshots = event.intAwayShots ?: ""
        view.tvAwayShots.text = awayshots.toString()

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
        Glide.with(this).load(url).into(view.ivHomeBadge)
    }

    override fun showAwayBadge(url: String?) {
        Glide.with(this).load(url).into(view.ivAwayBadge)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}