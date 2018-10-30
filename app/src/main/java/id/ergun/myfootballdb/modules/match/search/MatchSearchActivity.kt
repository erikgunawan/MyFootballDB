package id.ergun.myfootballdb.modules.match.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import id.ergun.myfootballdb.R
import id.ergun.myfootballdb.bases.adapters.BaseMatchAdapter
import id.ergun.myfootballdb.configs.EVENT
import id.ergun.myfootballdb.models.Event
import id.ergun.myfootballdb.modules.match.schedule.detail.MatchScheduleDetailActivity
import id.ergun.myfootballdb.utils.gone
import id.ergun.myfootballdb.utils.invisible
import id.ergun.myfootballdb.utils.visible
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MatchSearchActivity: AppCompatActivity(), MatchSearchContract.View,
        BaseMatchAdapter.ItemClickListener {

    private lateinit var view: MatchSearchUI

    private lateinit var presenter: MatchSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MatchSearchUI()
        view.setContentView(this)

        presenter = MatchSearchPresenter(this)
        presenter.onAttach(this)

        view.adapter = BaseMatchAdapter(view.eventList, this)
        view.rvEvent.adapter = view.adapter

        view.progressBar.gone()
    }

    override fun onItemClick(v: View, position: Int) {
        startActivity<MatchScheduleDetailActivity>(EVENT to view.adapter.getData()[position])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_match, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.isIconified = false

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty())
                    presenter.searchEvents(newText)
                return false
            }
        })
        return true
    }

    override fun showLoading() {
        view.progressBar.visible()
    }

    override fun hideLoading() {
        view.progressBar.invisible()
    }

    override fun resetDataList() {
        view.eventList.clear()
        view.adapter.notifyDataSetChanged()
    }

    override fun showDataList(data: List<Event>) {
        view.eventList.clear()
        view.eventList.addAll(data)
        view.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}