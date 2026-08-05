package client.project.tracker.worker

import org.koin.dsl.module

val workerModule = module {
    single {
        NotificationHelper(get())
    }
}