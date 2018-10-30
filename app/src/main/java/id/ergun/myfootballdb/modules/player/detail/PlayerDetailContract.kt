package id.ergun.myfootballdb.modules.player.detail

import id.ergun.myfootballdb.bases.views.BaseView
import id.ergun.myfootballdb.models.Player

class PlayerDetailContract {
    interface View: BaseView {
        fun showLoading()
        fun hideLoading()
        fun showData(player: Player)
    }

    interface Presenter {
        fun onAttach(T: BaseView)
        fun onDetach()
    }
}