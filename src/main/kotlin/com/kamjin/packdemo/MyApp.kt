package com.kamjin.packdemo

import com.kamjin.packdemo.view.*
import tornadofx.*

class MyApp: App(MainView::class, Styles::class)

fun main() {
    launch<MyApp>()
}