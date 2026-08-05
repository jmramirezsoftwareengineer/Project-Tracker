package client.project.tracker

import android.app.Application
import client.project.tracker.di.appModule
import client.project.tracker.worker.WorkerScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProjectApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProjectApplication)
            modules(appModule)
        }

        WorkerScheduler.schedule(this)
    }
}