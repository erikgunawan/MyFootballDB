package id.ergun.myfootballdb.bases.repositories

import id.ergun.myfootballdb.bases.models.DTOEventList

interface EventListRepositoryCallback {
    fun onDataLoaded(data: DTOEventList)
    fun onDataError()
}