package client.project.tracker.di

import client.project.tracker.domain.usecase.AddProjectUseCase
import client.project.tracker.domain.usecase.DeleteAllProjectsUseCase
import client.project.tracker.domain.usecase.DeleteProjectUseCase
import client.project.tracker.domain.usecase.FilterProjectsUseCase
import client.project.tracker.domain.usecase.GetProjectByIdUseCase
import client.project.tracker.domain.usecase.GetProjectsUseCase
import client.project.tracker.domain.usecase.SearchProjectsUseCase
import client.project.tracker.domain.usecase.UpdateProjectUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddProjectUseCase(get()) }

    factory { UpdateProjectUseCase(get()) }

    factory { DeleteProjectUseCase(get()) }

    factory { GetProjectsUseCase(get()) }

    factory { GetProjectByIdUseCase(get()) }

    factory { SearchProjectsUseCase(get()) }

    factory { FilterProjectsUseCase(get()) }

    factory { DeleteAllProjectsUseCase(get()) }
}