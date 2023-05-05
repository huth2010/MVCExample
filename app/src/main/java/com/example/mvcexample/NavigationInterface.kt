package com.example.mvcexample

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

interface NavigationInterface {
    fun navigateTo(fragment: Fragment)

}