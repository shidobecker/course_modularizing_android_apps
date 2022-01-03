package com.codingwithmitch.dotainfo.core

class Logger(private val tag: String, private val isDebug: Boolean = true) {
    fun log(msg: String) {
        if (!isDebug) {
            //production logging - Crashlytics
        } else {
            printLogD(tag, msg)
        }
    }


    companion object Factory {
        fun buildDebug(tag: String): Logger {
            return Logger(
                tag = tag, isDebug = true
            )
        }

        fun buildRelease(tag: String): Logger {
            return Logger(
                tag = tag, isDebug = false
            )
        }
    }

}

fun printLogD(tag: String, message: String) {
    println("$tag : $message")
}