package com.mdo.yoni.eshop.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.data.getSearchWords

class WordsAdapter(private val ctx: Context) : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private var items: List<String>? = getSearchWords(ctx)?.filter { x: String -> !x.isEmpty() }
    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    fun refresh() {
        val s = getSearchWords(ctx)
        if(s == null){
            items = null
        }else{
            items = s?.filter { x: String -> !x.isEmpty() }
        }
        super.notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(items!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.word_item, parent, false))
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun update(str: String) {
            v.findViewById<TextView>(R.id.contact_name).text = str
            v.findViewById<View>(R.id.delete).setTag(str)
        }

    }
}
