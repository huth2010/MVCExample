package com.example.mvcexample.model

interface DataInterface {
    val items: List<Counter>
    fun addOrUpdateItem(counter: Counter)
    fun removeItem(counter: Counter)
}