package client.project.tracker.di

import client.project.tracker.presentation.addedit.AddEditProjectViewModel
import client.project.tracker.presentation.details.ProjectDetailsViewModel
import client.project.tracker.presentation.home.HomeViewModel
import client.project.tracker.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getProjects = get(),
            searchProjects = get(),
            filterProjects = get(),
            deleteProject = get()
        )
    }

    viewModel {
        AddEditProjectViewModel(
            addProject = get(),
            updateProject = get(),
            getProjectById = get()
        )
    }

    viewModel {
        ProjectDetailsViewModel(
            getProjectById = get(),
            deleteProject = get()
        )
    }

    viewModel {
        SettingsViewModel(
            dataStore = get()
        )
    }
}