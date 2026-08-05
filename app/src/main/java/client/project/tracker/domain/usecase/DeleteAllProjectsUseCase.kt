package client.project.tracker.domain.usecase

import client.project.tracker.domain.repository.ProjectRepository

class DeleteAllProjectsUseCase (private val repository: ProjectRepository) {
    suspend operator fun invoke() {
        repository.deleteAllProjects()
    }
}