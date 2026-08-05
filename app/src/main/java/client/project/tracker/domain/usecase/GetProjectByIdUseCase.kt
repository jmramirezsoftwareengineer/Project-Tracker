package client.project.tracker.domain.usecase

import client.project.tracker.domain.Project
import client.project.tracker.domain.repository.ProjectRepository

class GetProjectByIdUseCase (private val repository: ProjectRepository) {
    suspend operator fun invoke(id: Long): Project? {
        return repository.getProject(id)
    }
}