package com.kamjin.packdemo.view

import com.kamjin.packdemo.*
import com.kamjin.packdemo.util.*
import javafx.geometry.*
import org.slf4j.*
import tornadofx.*


class MainView : View("Hello TornadoFX") {

    private val logger = LoggerFactory.getLogger(MainView::class.java)

    override val root = borderpane() {
        style {
            setMinWidth(400.0)
            setMinHeight(400.0)
            alignment = Pos.CENTER
            paddingAll = insets.all
        }

        top = label(title) {
            addClass(Styles.heading)
        }

        center = button("click then write log to file") {
            action {
                information("test", "hello ${ExtraConfig["hello"]}", title = "my title")
                logger.info("test info log >>>>>>>>>>>>>>>>>>>>>>")
            }
        }
    }
}