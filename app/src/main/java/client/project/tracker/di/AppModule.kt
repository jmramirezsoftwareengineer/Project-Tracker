package client.project.tracker.di
import client.project.tracker.worker.workerModule
import org.koin.dsl.module

val appModule = module {
    includes(
        databaseModule,
        dataStoreModule,
        networkModule,
        localModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
        workerModule
    )
}