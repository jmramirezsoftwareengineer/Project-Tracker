package client.project.tracker.domain.usecase

import client.project.tracker.domain.Project
import client.project.tracker.domain.repository.ProjectRepository

class AddProjectUseCase (private val repository: ProjectRepository) {
    suspend operator fun invoke(project: Project) {
        require(project.clientName.isNotBlank()) {
            "Client Name cannot be empty"
        }
        repository.addProject(project)
    }
}