package id.ergun.myfootballdb.db

import id.ergun.myfootballdb.configs.LEAGUE_ID
import id.ergun.myfootballdb.modules.matchschedule.Event

data class MatchFavorite(
        val id: Long?,
        val idEvent: Int?,
        val dateEvent: String?,
        val idHomeTeam: Int?,
        val homeTeam: String?,
        val homeScore: Int?,
        val homeFormation: String?,
        val homeGoalDetails: String?,
        val homeShots: Int?,
        val homeLineupGoalkeeper: String?,
        val homeLineupDefense: String?,
        val homeLineupMidfielder: String?,
        val homeLineupForward: String?,
        val homeLineupSubstitutes: String?,
        val homeBadge: String?,
        val idAwayTeam: Int?,
        val awayTeam: String?,
        val awayScore: Int?,
        val awayFormation: String?,
        val awayGoalDetails: String?,
        val awayShots: Int?,
        val awayLineupGoalkeeper: String?,
        val awayLineupDefense: String?,
        val awayLineupMidfielder: String?,
        val awayLineupForward: String?,
        val awayLineupSubstitutes: String?,
        val awayBadge: String?
) {

    companion object {
        const val TABLE_MATCH_FAVORITE: String = "T_MATCH_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"

        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_FORMATION: String = "HOME_FORMATION"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val HOME_LINEUP_GOALKEEPER: String = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELDER: String = "HOME_LINEUP_MIDFIELDER"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES: String = "HOME_LINEUP_SUBSTITUTES"
        const val HOME_BADGE: String = "HOME_BADGE"

        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_FORMATION: String = "AWAY_FORMATION"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val AWAY_LINEUP_GOALKEEPER: String = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELDER: String = "AWAY_LINEUP_MIDFIELDER"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES: String = "AWAY_LINEUP_SUBSTITUTES"
        const val AWAY_BADGE: String = "AWAY_BADGE"
    }

    object Mapper {
        fun toEvent(matchFavorite: MatchFavorite) =
                Event(
                        matchFavorite.idEvent,
                        null,
                        null,
                        null,
                        null,
                        LEAGUE_ID.toInt(),
                        null,
                        null,
                        matchFavorite.homeTeam,
                        matchFavorite.awayTeam,
                        matchFavorite.homeScore,
                        null,
                        matchFavorite.awayScore,
                        matchFavorite.homeGoalDetails,
                        null,
                        null,
                        matchFavorite.homeLineupGoalkeeper,
                        matchFavorite.homeLineupDefense,
                        matchFavorite.homeLineupMidfielder,
                        matchFavorite.homeLineupForward,
                        matchFavorite.homeLineupSubstitutes,
                        matchFavorite.homeFormation,
                        null,
                        null,
                        matchFavorite.awayGoalDetails,
                        matchFavorite.awayLineupGoalkeeper,
                        matchFavorite.awayLineupDefense,
                        matchFavorite.awayLineupMidfielder,
                        matchFavorite.awayLineupForward,
                        matchFavorite.awayLineupSubstitutes,
                        matchFavorite.awayFormation,
                        matchFavorite.homeShots,
                        matchFavorite.awayShots,
                        matchFavorite.dateEvent,
                        null,
                        null,
                        matchFavorite.idHomeTeam,
                        matchFavorite.idAwayTeam,
                        null
                )
    }
}