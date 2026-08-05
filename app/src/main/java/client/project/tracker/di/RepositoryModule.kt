package client.project.tracker.di

import client.project.tracker.domain.repository.ProjectRepository
import client.project.tracker.domain.repository.ProjectRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<ProjectRepository> {
        ProjectRepositoryImpl(
            dao = get(),
            jsonDataSource = get()
        )
    }
}