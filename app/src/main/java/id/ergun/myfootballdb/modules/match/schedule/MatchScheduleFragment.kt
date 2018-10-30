package id.ergun.myfootballdb.modules.match.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.ergun.myfootballdb.R.id.search_match
import id.ergun.myfootballdb.R.layout.fragment_match
import id.ergun.myfootballdb.R.menu.menu_match_schedule
import id.ergun.myfootballdb.bases.adapters.BaseViewPagerAdapter
import id.ergun.myfootballdb.modules.match.schedule.next.MatchScheduleNextFragment
import id.ergun.myfootballdb.modules.match.schedule.prev.MatchSchedulePrevFragment
import id.ergun.myfootballdb.modules.match.search.MatchSearchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class MatchScheduleFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BaseViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(MatchSchedulePrevFragment(), "Prev Match")
        adapter.populateFragment(MatchScheduleNextFragment(), "Next Match")
        viewpager_match.adapter = adapter
        tab_match.setupWithViewPager(viewpager_match)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(menu_match_schedule, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            search_match -> {
                startActivity<MatchSearchActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}