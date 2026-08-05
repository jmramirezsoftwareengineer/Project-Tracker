package client.project.tracker.domain.usecase

import client.project.tracker.domain.Project
import client.project.tracker.domain.ProjectStatus
import client.project.tracker.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow

class FilterProjectsUseCase(
    private val repository: ProjectRepository
) {
    operator fun invoke(status: ProjectStatus): Flow<List<Project>> {
        return repository.filterProjects(status)
    }
}