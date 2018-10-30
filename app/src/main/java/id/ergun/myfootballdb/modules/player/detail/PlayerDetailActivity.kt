package id.ergun.myfootballdb.modules.player.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.configs.PLAYER
import id.ergun.myfootballdb.models.Player
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity: AppCompatActivity(), PlayerDetailContract.View {

    private lateinit var presenter: PlayerDetailPresenter

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PlayerDetailPresenter(this)
        presenter.onAttach(this)

        player = intent.getParcelableExtra(PLAYER)

        presenter.getDetailPlayer(player.idPlayer.toString())

        fillView(player)
    }

    private fun fillView(player: Player) {
        title = player.strPlayer
        tv_player_height.text = player.strHeight
        tv_player_weight.text = player.strWeight
        tv_player_position.text = player.strPosition
        tv_player_description.text = player.strDescriptionEN
        Glide.with(this).load(player.strFanart1).into(iv_player_header)
    }

    override fun showData(player: Player) {
        fillView(player)
    }

    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invisible()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}