package client.project.tracker.di

import androidx.room.Room
import client.project.tracker.data.database.ProjectDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ProjectDatabase::class.java,
            ProjectDatabase.DATABASE_NAME
        ).build()
    }

    single {
        get<ProjectDatabase>().projectDao()
    }
}