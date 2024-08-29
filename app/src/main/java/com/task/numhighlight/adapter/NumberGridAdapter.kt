package com.task.numhighlight.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.task.numhighlight.interf.HighlightRule

class NumberGridAdapter(
    context: Context,
    private val numbers: List<Int>,
    private var highlightRule: HighlightRule
) : ArrayAdapter<Int>(context, android.R.layout.simple_list_item_1, numbers) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return try {
            val view = super.getView(position, convertView, parent) as TextView
            val number = getItem(position) ?: 1

            if (highlightRule.shouldHighlight(number)) {
                view.setBackgroundColor(Color.YELLOW)
            } else {
                view.setBackgroundColor(Color.TRANSPARENT)
            }

            view
        } catch (e: Exception) {
            Log.e("NumberGridAdapter", "Error in getView at position $position", e)

            convertView ?: super.getView(position, convertView, parent)
        }
    }

    fun updateRule(newRule: HighlightRule) {
        try {
            highlightRule = newRule
            notifyDataSetChanged()
        } catch (e: Exception) {
            Log.e("NumberGridAdapter", "Error updating rule", e)
        }
    }
}
