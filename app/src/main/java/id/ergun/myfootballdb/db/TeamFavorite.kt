package id.ergun.myfootballdb.db

import id.ergun.myfootballdb.models.Team

data class TeamFavorite(
        val id: Long?,
        val idTeam: Int? = null,
        val nameTeam: String? = null,
        val descriptionTeam: String? = null,
        val formedYearTeam: Int? = null,
        val stadiumTeam: String? = null,
        val badgeTeam: String? = null
) {

    companion object {
        const val TABLE_TEAM_FAVORITE: String = "T_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val NAME_TEAM: String = "NAME_TEAM"
        const val DESCRIPTION_TEAM: String = "DESCRIPTION_TEAM"
        const val FORMED_YEAR_TEAM: String = "FORMED_YEAR_TEAM"
        const val STADIUM_TEAM: String = "STADIUM_TEAM"
        const val BADGE_TEAM: String = "BADGE_TEAM"
    }

    object Mapper {
        fun toTeam(teamFavorite: TeamFavorite) =
                Team(
                        idTeam = teamFavorite.idTeam,
                        strTeam = teamFavorite.nameTeam,
                        strDescriptionEN = teamFavorite.descriptionTeam,
                        intFormedYear = teamFavorite.formedYearTeam,
                        strStadium = teamFavorite.stadiumTeam,
                        strTeamBadge = teamFavorite.badgeTeam
                )
    }
}