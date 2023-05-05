package com.example.mvcexample.fragments

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvcexample.R
import com.example.mvcexample.model.Counter
import java.util.Collections


class CounterAdapter(private val callBack: (id: String) -> Unit) :
    RecyclerView.Adapter<CounterAdapter.Companion.CounterHolder>() {
    private val counters: MutableList<Counter> =mutableListOf()
    fun updateList(data: List<Counter>){
        counters.clear()
        counters.addAll(data)
        notifyDataSetChanged()

    }

    companion object {
        class CounterHolder(view: View, val callBack: (id: String) -> Unit) :
            RecyclerView.ViewHolder(view) {
            fun bind(counter: Counter) {
                itemView.findViewById<TextView>(R.id.value).text = "${counter.value}"
                itemView.findViewById<TextView>(R.id.date).text = itemView.context.toDate(counter.dateInWillis)
                itemView.findViewById<CardView>(R.id.cardItem).setOnClickListener {
                    callBack(counter.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =CounterHolder(
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false),callBack
        )

    override fun getItemCount()=counters.size

    override fun onBindViewHolder(holder: CounterHolder, position: Int) {
    holder.bind(counters[position] )
    }

    fun moveItem(from: Int, to: Int) {
        Collections.swap(counters, to, from)
        notifyItemMoved(from, to)
    }


}
private fun Context.toDate(dateInWillis: Long): CharSequence? {
    val flag = DateUtils.FORMAT_ABBREV_TIME or DateUtils.FORMAT_SHOW_DATE
    return DateUtils.formatDateTime(this, dateInWillis, flag)
}
//private fun <T> Iterable<T>. swap(from: Int, to: Int): Iterable<T> {
//    val fromItem: T?= find { indexOf(it) == from }
//    val toItem: T? = find { indexOf(it) == to }
//    return mapIndexed {i, existing -> if (i == from && toItem != null) toItem else if(i==to && fromItem!=null)fromItem else existing}
//    }