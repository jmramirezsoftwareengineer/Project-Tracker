package client.project.tracker.domain.usecase

import client.project.tracker.domain.Project
import client.project.tracker.domain.repository.ProjectRepository

class DeleteProjectUseCase(private val repository: ProjectRepository) {
    suspend operator fun invoke(project: Project) {
        repository.deleteProject(project)
    }
}