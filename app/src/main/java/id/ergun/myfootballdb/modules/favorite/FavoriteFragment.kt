package id.ergun.myfootballdb.modules.match.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.SearchView
import android.view.*
import id.ergun.myfootballdb.R.id.action_search
import id.ergun.myfootballdb.R.layout.fragment_match
import id.ergun.myfootballdb.R.menu.menu_search
import id.ergun.myfootballdb.modules.match.schedule.next.EmptyFragment
import kotlinx.android.synthetic.main.fragment_match.*

class MatchScheduleFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rootView = inflater.inflate(fragment_match, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(EmptyFragment(), "Prev Match")
        adapter.populateFragment(EmptyFragment(), "Next Match")
        viewpager_match.adapter = adapter
        tab_match.setupWithViewPager(viewpager_match)
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){

        var fragmentList = arrayListOf<Fragment>()
        var titleList = arrayListOf<String>()

        fun populateFragment(fragment: Fragment,title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }
        override fun getItem(position: Int) = fragmentList[position]

        override fun getCount() = fragmentList.size

        override fun getPageTitle(position: Int) = titleList[position]
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(menu_search, menu)

        val searchView = menu?.findItem(action_search)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                context?.startActivity<SearchMatchActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })


    }
}