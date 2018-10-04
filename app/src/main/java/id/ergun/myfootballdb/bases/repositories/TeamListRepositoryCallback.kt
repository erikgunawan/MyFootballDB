package id.ergun.myfootballdb.bases.repositories

import id.ergun.myfootballdb.bases.models.DTOTeamList

interface TeamListRepositoryCallback {
    fun onDataLoaded(data: DTOTeamList, side: String)
    fun onDataError(side: String)
}