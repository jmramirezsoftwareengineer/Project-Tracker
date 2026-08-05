package client.project.tracker.data.dao

import androidx.room.*
import client.project.tracker.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT COUNT(*) FROM projects")
    suspend fun count(): Int

    @Query("""
        SELECT *
        FROM projects
        ORDER BY dueDate ASC
    """)
    fun getProjects(): Flow<List<ProjectEntity>>

    @Query("""
        SELECT *
        FROM projects
        WHERE id = :id
    """)
    suspend fun getProject(id: Long): ProjectEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        projects: List<ProjectEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ProjectEntity): Long

    @Update
    suspend fun update(todo: ProjectEntity)

    @Delete
    suspend fun delete(todo: ProjectEntity)

    @Query("""
        DELETE FROM projects
    """)
    suspend fun deleteAll()

    @Query("""
        SELECT * FROM projects
        WHERE projectName LIKE '%' || :query || '%'
        OR clientName LIKE '%' || :query || '%'
        OR status LIKE '%' || :query || '%'
    """)
    fun search(query: String): Flow<List<ProjectEntity>>

    @Query("""
        SELECT * FROM projects
        WHERE status=:status
    """)
    fun filterByStatus(status: String): Flow<List<ProjectEntity>>

    @Query("""
        SELECT *
        FROM projects
        WHERE dueDate BETWEEN :today AND :targetDate
        AND status != 'COMPLETED'
        AND status != 'CANCELLED'
        ORDER BY dueDate
    """)
    suspend fun getProjectsDueBetween(
        today: String,
        targetDate: String
    ): List<ProjectEntity>
}