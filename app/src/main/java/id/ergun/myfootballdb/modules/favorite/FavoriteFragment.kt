package id.ergun.myfootballdb.modules.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ergun.myfootballdb.R.layout.fragment_favorite
import id.ergun.myfootballdb.bases.adapters.BaseViewPagerAdapter
import id.ergun.myfootballdb.modules.favorite.match.MatchFavoriteFragment
import id.ergun.myfootballdb.modules.favorite.team.TeamFavoriteFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BaseViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(MatchFavoriteFragment(), "Matches")
        adapter.populateFragment(TeamFavoriteFragment(), "Teams")
        viewpager_favorite.adapter = adapter
        tab_favorite.setupWithViewPager(viewpager_favorite)
    }
}