package com.example.mvcexample.fragments

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mvcexample.NavigationInterface
import com.example.mvcexample.R
import com.example.mvcexample.model.Counter
import com.example.mvcexample.model.DataImplement
import com.example.mvcexample.model.generateFakeData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.time.ExperimentalTime


class ListCounterFragment : Fragment() {

    private val navigationController by lazy {
        requireActivity() as? NavigationInterface
    }

    private val adapter by lazy {
        CounterAdapter(onItemClick)
    }
    private val onItemClick: (id: String) -> Unit =
        { id ->
            navigationController?.navigateTo(CounterFragment.newInstance(id))
        }

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or START or END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter = recyclerView.adapter as CounterAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.moveItem(from, to)
                Log.e("111111111111", "onMove: ")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.e("111111111111", "onSwiped: ")

            }

        }
        ItemTouchHelper(simpleItemTouchCallback)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        adapter.updateList(DataImplement.instance.items)
        DataImplement.instance.neeUpdate = {
            adapter.updateList(DataImplement.instance.items)
        }
        itemTouchHelper.attachToRecyclerView(recyclerView)
        view.findViewById<FloatingActionButton>(R.id.newCounter).setOnClickListener {
            navigationController?.navigateTo(CounterFragment.newInstance())
        }
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {
        fun newInstance() = ListCounterFragment()
    }

}