package client.project.tracker.domain.repository

import client.project.tracker.domain.Project
import client.project.tracker.domain.ProjectStatus
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getProjects() : Flow<List<Project>>

    suspend fun getProject(id: Long) : Project?

    suspend fun addProject(project: Project)

    suspend fun updateProject(project: Project)

    suspend fun deleteProject(project: Project)

    suspend fun deleteAllProjects()

    fun searchProjects(query: String): Flow<List<Project>>

    fun filterProjects(status: ProjectStatus): Flow<List<Project>>
}