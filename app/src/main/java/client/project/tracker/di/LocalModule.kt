package client.project.tracker.di

import client.project.tracker.data.local.LocalJsonDataSource
import org.koin.dsl.module

val localModule = module {
    single {
        LocalJsonDataSource(get())
    }
}