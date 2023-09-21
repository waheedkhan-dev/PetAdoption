package com.codenablers.petadoption

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

private const val TAG = "PetAdoptionApp"

@HiltAndroidApp
open class PetAdoptionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setTimberConfiguration()
    }

    protected open fun setTimberConfiguration() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.tag(TAG).i("Configure Timber for logs")
        }
    }

}