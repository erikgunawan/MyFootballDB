package id.ergun.myfootballdb.bases.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import id.ergun.myfootballdb.models.League

class SpinnerLeagueAdapter(context: Context, resource: Int, val list: List<League>):
        ArrayAdapter<League>(context, resource, list) {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): League = list[position]

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val text1 = view.findViewById(android.R.id.text1) as TextView
        text1.text = list[position].strLeague
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?,
                        parent: ViewGroup?): View {
        val view = super.getDropDownView(position, convertView, parent)
        val text1 = view.findViewById(android.R.id.text1) as TextView
        text1.text = list[position].strLeague
        return view
    }
}