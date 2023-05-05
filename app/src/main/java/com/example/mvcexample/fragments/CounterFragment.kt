package com.example.mvcexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.mvcexample.R
import com.example.mvcexample.model.Counter
import com.example.mvcexample.model.DataImplement
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CounterFragment : Fragment() {

    private var value = 0
    private var currentCounter: Counter? = null
    lateinit var tv2: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_counter, container, false).also {
            arguments?.getString(ID_KEY)?.let { id ->
                currentCounter = DataImplement.instance.items.firstOrNull { it.id == id }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv2 = view.findViewById<TextView>(R.id.textView2)
        value = currentCounter?.value ?: 0
        showData()
        setHasOptionsMenu(true)

        view.findViewById<FloatingActionButton>(com.google.android.material.R.id.postLayout)
            .setOnClickListener {
                value += 1
                showData()
            }
        view.findViewById<FloatingActionButton>(R.id.plus2).setOnClickListener {
            value += 2
            showData()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.counter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionsave) {
            Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show()
            val counter = currentCounter?.copy(value = value) ?: Counter(
                value = value,
                dateInWillis = System.currentTimeMillis()
            )
            DataImplement.instance.addOrUpdateItem(counter)
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showData() {
        tv2.text = "$value"
    }

    companion object {
        private const val ID_KEY = "id_key"
        fun newInstance(id: String? = null) = CounterFragment().apply {
            arguments = Bundle().apply {
                putString(ID_KEY, id)
            }
        }
    }
}