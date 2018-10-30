package id.ergun.myfootballdb.modules.player

import id.ergun.myfootballdb.bases.models.DTOPlayerList
import id.ergun.myfootballdb.bases.views.BaseView

class PlayerContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showDataList(data: DTOPlayerList)
        fun resetDataList()
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}