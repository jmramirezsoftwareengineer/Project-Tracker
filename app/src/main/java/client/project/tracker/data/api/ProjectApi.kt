package client.project.tracker.data.api

import client.project.tracker.data.dto.ProjectDto
import retrofit2.http.GET

interface ProjectApi {

    // Future Retrofit endpoints
    @GET("projects")
    suspend fun getProjects(): List<ProjectDto>
}
