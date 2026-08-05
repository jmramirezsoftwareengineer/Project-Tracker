package client.project.tracker.domain.usecase

data class ProjectUseCases (
    val getProjects: GetProjectsUseCase,
    val getProjectById: GetProjectByIdUseCase,
    val addProject: AddProjectUseCase,
    val updateProject: UpdateProjectUseCase,
    val deleteProject: DeleteProjectUseCase,
    val deleteAllProjects: DeleteAllProjectsUseCase
)