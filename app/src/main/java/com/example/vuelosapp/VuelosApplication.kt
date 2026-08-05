package com.example.vuelosapp

import android.app.Application
import com.example.vuelosapp.data.AppContainer
import com.example.vuelosapp.data.VuelosAppContainer

class VuelosApplication: Application() {
    //interface
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
                    //implementacion
        container = VuelosAppContainer(this)
    }


}