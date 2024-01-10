package com.southernsunrise.notesappbottomnav.application

import android.app.Application
import android.content.Context
import com.southernsunrise.notesappbottomnav.di.DI
import java.util.Timer
import java.util.TimerTask


class NotesApplication : Application() {

    companion object {
        lateinit var instance: NotesApplication
            private set

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        DI.init(this)
    }
}