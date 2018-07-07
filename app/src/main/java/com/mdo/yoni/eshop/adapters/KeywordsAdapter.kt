package com.mdo.yoni.eshop.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.data.getSearchWords

class KeywordsAdapter(private val ctx: Context, private val list: List<String>) : RecyclerView.Adapter<KeywordsAdapter.ViewHolder>() {

    private var items: List<String> = list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.keyword_item, parent, false))
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun update(str: String) {
            v.findViewById<TextView>(R.id.contact_name).text = str
        }
    }
}
