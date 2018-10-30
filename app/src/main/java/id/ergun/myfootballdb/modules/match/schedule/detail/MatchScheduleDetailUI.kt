package id.ergun.myfootballdb.modules.match.schedule.detail

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.widget.SwipeRefreshLayout
import android.text.TextUtils
import android.view.Gravity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.R.color.colorAccent
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchScheduleDetailUI : AnkoComponent<MatchScheduleDetailActivity> {

    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    lateinit var tvDateEvent: TextView
    lateinit var tvTimeEvent: TextView

    lateinit var ivHomeBadge: ImageView
    lateinit var tvHomeTeam: TextView
    lateinit var tvHomeScore: TextView
    lateinit var tvHomeGoalDetails: TextView
    lateinit var tvHomeShots: TextView
    lateinit var tvHomeFormation: TextView
    lateinit var tvHomeLineupGoalkeeper: TextView
    lateinit var tvHomeLineupDefense: TextView
    lateinit var tvHomeLineupMidfielder: TextView
    lateinit var tvHomeLineupForward: TextView
    lateinit var tvHomeLineupSubtitutes: TextView

    lateinit var ivAwayBadge: ImageView
    lateinit var tvAwayTeam: TextView
    lateinit var tvAwayScore: TextView
    lateinit var tvAwayGoalDetails: TextView
    lateinit var tvAwayShots: TextView
    lateinit var tvAwayFormation: TextView
    lateinit var tvAwayLineupGoalkeeper: TextView
    lateinit var tvAwayLineupDefense: TextView
    lateinit var tvAwayLineupMidfielder: TextView
    lateinit var tvAwayLineupForward: TextView
    lateinit var tvAwayLineupSubtitutes: TextView

    override fun createView(ui: AnkoContext<MatchScheduleDetailActivity>) = with(ui) {

        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false

                    relativeLayout {
                        lparams(width = matchParent, height = matchParent)
                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                            gravity = Gravity.CENTER
                        }

                        verticalLayout {
                            padding = dip(10)
                            lparams(width = matchParent, height = wrapContent)

                            tvDateEvent = textView {
                                id = R.id.tv_date_event
                                gravity = Gravity.CENTER_HORIZONTAL
                                textSize = 14F
                                textColor = Color.BLUE
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = matchParent, height = wrapContent) {
                                margin = dip(5)
                            }

                            tvTimeEvent = textView {
                                id = R.id.tv_time_event
                                gravity = Gravity.CENTER_HORIZONTAL
                                textSize = 14F
                                textColor = Color.BLUE
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = matchParent, height = wrapContent) {
                                margin = dip(5)
                            }

                            linearLayout {
                                padding = dip(16)
                                lparams(width = matchParent, height = wrapContent)

                                verticalLayout {

                                    ivHomeBadge = imageView {
                                        id = R.id.iv_home_badge
                                    }.lparams(width = dip(50), height = dip(50)) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }

                                    tvHomeTeam = textView {
                                        id = R.id.tv_home_team
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        singleLine = true
                                        typeface = Typeface.DEFAULT_BOLD
                                        ellipsize = TextUtils.TruncateAt.END
                                    }.lparams(width = matchParent, height = wrapContent)

                                    tvHomeFormation = textView {
                                        id = R.id.tv_home_formation
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        singleLine = true
                                        ellipsize = TextUtils.TruncateAt.END
                                    }.lparams(width = wrapContent, height = wrapContent)

                                }.lparams(width = 0, height = wrapContent, weight = 0.25F)

                                verticalLayout {

                                    tvHomeScore = textView {
                                        id = R.id.tv_home_score
                                        gravity = Gravity.CENTER
                                        typeface = Typeface.DEFAULT_BOLD
                                        textSize = 24F
                                    }.lparams(width = matchParent, height = matchParent)

                                }.lparams(width = 0, height = matchParent, weight = 0.2F)

                                verticalLayout {

                                    textView("VS") {
                                        gravity = Gravity.CENTER
                                        typeface = Typeface.DEFAULT_BOLD
                                        textSize = 14F
                                    }.lparams(width = matchParent, height = matchParent)

                                }.lparams(width = 0, height = matchParent, weight = 0.1F)

                                verticalLayout {

                                    tvAwayScore = textView {
                                        id = R.id.tv_away_score
                                        gravity = Gravity.CENTER
                                        typeface = Typeface.DEFAULT_BOLD
                                        textSize = 24F
                                    }.lparams(width = matchParent, height = matchParent)

                                }.lparams(width = 0, height = matchParent, weight = 0.2F)

                                verticalLayout {

                                    ivAwayBadge = imageView {
                                        id = R.id.iv_away_badge
                                    }.lparams(width = dip(50), height = dip(50)) {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }

                                    tvAwayTeam = textView {
                                        id = R.id.tv_away_team
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        singleLine = true
                                        typeface = Typeface.DEFAULT_BOLD
                                        ellipsize = TextUtils.TruncateAt.END
                                    }.lparams(width = matchParent, height = wrapContent)

                                    tvAwayFormation = textView {
                                        id = R.id.tv_away_formation
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        singleLine = true
                                        ellipsize = TextUtils.TruncateAt.END
                                    }.lparams(width = wrapContent, height = wrapContent)

                                }.lparams(width = 0, height = wrapContent, weight = 0.25F)
                            }

                            verticalLayout {
                                padding = dip(16)
                                lparams(width = matchParent, height = wrapContent)

                                view {
                                    backgroundColor = Color.GRAY
                                }.lparams(width = matchParent, height = dip(1))

                                // Goals
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeGoalDetails = textView {
                                        id = R.id.tv_home_goal_details
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)

                                    textView("Goals") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F)

                                    tvAwayGoalDetails = textView {
                                        id = R.id.tv_away_goal_details
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)
                                }

                                // Shots
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeShots = textView {
                                        id = R.id.tv_home_shots
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)

                                    textView("Shots") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F)

                                    tvAwayShots = textView {
                                        id = R.id.tv_away_shots
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F)
                                }

                                view {
                                    backgroundColor = Color.GRAY
                                }.lparams(width = matchParent, height = dip(1))
                            }

                            // Lineups
                            verticalLayout {
                                padding = dip(16)
                                lparams(width = matchParent, height = wrapContent)

                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    textView("Lineups") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        textSize = 16F
                                        textColor = Color.BLUE
                                        typeface = Typeface.DEFAULT_BOLD
                                    }.lparams(width = matchParent, height = wrapContent)

                                }

                                // Goal Keeper
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeLineupGoalkeeper = textView {
                                        id = R.id.tv_home_lineup_goalkeeper
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }

                                    textView("Goal Keeper") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F) {
                                        margin = dip(5)
                                    }

                                    tvAwayLineupGoalkeeper = textView {
                                        id = R.id.tv_away_lineup_goalkeeper
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }
                                }

                                // Defense
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeLineupDefense = textView {
                                        id = R.id.tv_home_lineup_defense
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }

                                    textView("Defense") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F) {
                                        margin = dip(5)
                                    }

                                    tvAwayLineupDefense = textView {
                                        id = R.id.tv_away_lineup_defense
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }
                                }

                                // Midfielder
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeLineupMidfielder = textView {
                                        id = R.id.tv_home_lineup_midfielder
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }

                                    textView("Midfielder") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F) {
                                        margin = dip(5)
                                    }

                                    tvAwayLineupMidfielder = textView {
                                        id = R.id.tv_away_lineup_midfielder
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }
                                }

                                // Forward
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeLineupForward = textView {
                                        id = R.id.tv_home_lineup_forward
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }

                                    textView("Forward") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F) {
                                        margin = dip(5)
                                    }

                                    tvAwayLineupForward = textView {
                                        id = R.id.tv_away_lineup_forward
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }
                                }

                                // Subtitutes
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)

                                    tvHomeLineupSubtitutes = textView {
                                        id = R.id.tv_home_lineup_subtitutes
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }

                                    textView("Subtitutes") {
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams(width = 0, height = wrapContent, weight = 0.3F) {
                                        margin = dip(5)
                                    }

                                    tvAwayLineupSubtitutes = textView {
                                        id = R.id.tv_away_lineup_subtitutes
                                        gravity = Gravity.END
                                    }.lparams(width = 0, height = wrapContent, weight = 0.35F) {
                                        margin = dip(5)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}