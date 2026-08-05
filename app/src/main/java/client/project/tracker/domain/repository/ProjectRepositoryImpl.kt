package client.project.tracker.domain.repository

import client.project.tracker.data.dao.ProjectDao
import client.project.tracker.data.local.LocalJsonDataSource
import client.project.tracker.data.mapper.toDomain
import client.project.tracker.data.mapper.toEntity
import client.project.tracker.domain.Project
import client.project.tracker.domain.ProjectStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProjectRepositoryImpl(
    private val dao: ProjectDao,
    private val jsonDataSource: LocalJsonDataSource
) : ProjectRepository{

    override fun getProjects(): Flow<List<Project>> = flow {

        // Populate Room from JSON on first launch
        if (dao.count() == 0) {

            val projects = jsonDataSource
                .getProjects()
                .map { it.toDomain().toEntity() }

            dao.insertAll(projects)
        }

        emitAll(
            dao.getProjects().map { entities ->
                entities.map { it.toDomain() }
            }
        )
    }

    override suspend fun getProject(id: Long): Project? {
        return dao.getProject(id)?.toDomain()
    }

    override suspend fun addProject(project: Project) {
        dao.insert(project.toEntity())
    }

    override suspend fun updateProject(project: Project) {
        dao.update(project.toEntity())
    }

    override suspend fun deleteProject(project: Project) {
        dao.delete(project.toEntity())
    }

    override suspend fun deleteAllProjects() {
        dao.deleteAll()
    }

    override fun searchProjects(query: String): Flow<List<Project>> {
        return dao.search(query).map { projects -> projects.map { it.toDomain() } }
    }

    override fun filterProjects(status: ProjectStatus): Flow<List<Project>> {
        return dao.filterByStatus(status.toString()).map { projects -> projects.map { it.toDomain() } }
    }
}