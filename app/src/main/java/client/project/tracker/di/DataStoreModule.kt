package client.project.tracker.di

import client.project.tracker.presentation.settings.SettingsDataStore
import org.koin.dsl.module

val dataStoreModule = module {
    single {
        SettingsDataStore(get())
    }
}