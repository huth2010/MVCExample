package com.example.mvcexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.mvcexample.fragments.ListCounterFragment
import kotlin.time.ExperimentalTime

class MainActivity : AppCompatActivity(), NavigationInterface {

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.containerSimple, ListCounterFragment.newInstance())
                .commit()
        }
    }

    override fun navigateTo(fragment: Fragment) {
        if (supportFragmentManager.fragments.size == 1) {
            supportFragmentManager.beginTransaction()
                .add(R.id.containerSimple, fragment).commitNow()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.fragments.last()).commitNow()
        } else {
            super.onBackPressed()
        }
    }
}