package id.ergun.myfootballdb.bases.presenters

import id.ergun.myfootballdb.bases.views.BaseView

interface BasePresenter<T: BaseView> {
    fun onAttach(view: T)
    fun onDetach()
}

