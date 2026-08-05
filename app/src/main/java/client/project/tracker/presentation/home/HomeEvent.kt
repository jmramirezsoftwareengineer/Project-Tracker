package client.project.tracker.presentation.home

import client.project.tracker.domain.ProjectStatus

sealed interface HomeEvent {

    data class Search(val query: String) : HomeEvent

    data class Filter(val status: String?) : HomeEvent

    data class Delete(val projectId: Long) : HomeEvent

    data object Refresh : HomeEvent
}