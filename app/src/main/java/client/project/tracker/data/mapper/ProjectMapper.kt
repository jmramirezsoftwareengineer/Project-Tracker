package client.project.tracker.data.mapper

import client.project.tracker.data.dto.ProjectDto
import client.project.tracker.data.entity.ProjectEntity
import client.project.tracker.domain.Project

fun ProjectEntity.toDomain(): Project {
    return Project(
        id = id,
        clientName = clientName,
        projectName = projectName,
        description = description,
        status = status,
        priority = priority,
        startDate = startDate,
        dueDate = dueDate
    )
}

fun ProjectDto.toDomain(): Project {
    return Project(
        id = id,
        clientName = clientName,
        projectName = projectName,
        description = description,
        status = status,
        priority = priority,
        startDate = startDate,
        dueDate = dueDate
    )
}

fun Project.toEntity(): ProjectEntity {
    return ProjectEntity(
        id = id,
        clientName = clientName,
        projectName = projectName,
        description = description,
        status = status,
        priority = priority,
        startDate = startDate,
        dueDate = dueDate
    )
}