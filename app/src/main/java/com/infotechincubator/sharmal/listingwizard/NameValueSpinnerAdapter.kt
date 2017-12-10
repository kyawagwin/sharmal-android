package com.infotechincubator.sharmal.listingwizard

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.infotechincubator.sharmal.model.NameValueModel


/**
 * Created by kyawagwin on 10/12/17.
 */
class NameValueSpinnerAdapter constructor(
        context: Context,
        viewResourceId: Int,
        val values: Array<NameValueModel>
): ArrayAdapter<NameValueModel>(context, viewResourceId, values) {
    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): NameValueModel {
        return values[position]
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        val label = TextView(context)
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.text = values[position].name

        // And finally return your dynamic (or custom) view for each spinner item
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val label = TextView(context)
        label.text = values[position].name

        return label
    }
}