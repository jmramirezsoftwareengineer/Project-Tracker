package client.project.tracker.presentation.home

import client.project.tracker.domain.Project

data class HomeUiState(
    val isLoading: Boolean = false,
    val projects: List<Project> = emptyList(),
    val searchQuery: String = "",
    val selectedStatus: String? = null,
    val error: String? = null
    )