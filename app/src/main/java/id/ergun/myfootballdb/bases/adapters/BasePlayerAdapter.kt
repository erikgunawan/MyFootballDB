package id.ergun.myfootballdb.bases.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import id.ergun.myfootballdb.R.id.*
import id.ergun.myfootballdb.bases.ui.match.BasePlayerAdapterUI
import id.ergun.myfootballdb.models.Player
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class BasePlayerAdapter(private val players: List<Player>,
                        private val listener: (BasePlayerAdapter.ItemClickListener)): RecyclerView.Adapter<BasePlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = PlayerViewHolder(BasePlayerAdapterUI().createView(AnkoContext.create(parent.context, parent)))
        view.itemView.setOnClickListener {
            listener.onItemClick(it, view.adapterPosition)
        }
        return view
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) = holder.bindItem(players[position])

    fun getData(): List<Player> = players

    class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val playerCutOut: ImageView = view.find(iv_player_cutout)
        private val playerName: TextView = view.find(tv_player_name)
        private val playerPosition: TextView = view.find(tv_player_position)

        fun bindItem(player: Player) {
            Glide.with(itemView.context).load(player.strCutout).into(playerCutOut)
            playerName.text = player.strPlayer
            playerPosition.text = player.strPosition
        }
    }

    interface ItemClickListener {
        fun onItemClick(v: View, position: Int)
    }
}