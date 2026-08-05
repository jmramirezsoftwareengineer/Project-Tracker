package client.project.tracker.domain.usecase

import client.project.tracker.domain.Project
import client.project.tracker.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow

class GetProjectsUseCase(private val repository: ProjectRepository) {
    operator fun invoke(): Flow<List<Project>> {
        return repository.getProjects()
    }
}