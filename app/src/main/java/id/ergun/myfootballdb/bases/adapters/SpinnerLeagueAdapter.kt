package id.ergun.myfootballdb.bases.adapters

import android.content.Context
import android.graphics.Color
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ArrayAdapter
import id.ergun.myfootballdb.models.Player

class BaseSpinnerAdapter<T>(context: Context, resource: Int, val list: ArrayList<T>):
        ArrayAdapter<T>(context, resource, list) {

    override fun getCount(): Int = list.size


    override fun getItem(position: Int): T = list[position]

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.setText(values[position].getName())

        return label
    }

    override fun getDropDownView(position: Int, convertView: View,
                        parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.setText(values[position].getName())

        return label
    }
}