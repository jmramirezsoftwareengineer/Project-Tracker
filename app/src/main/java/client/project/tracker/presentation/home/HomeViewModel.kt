package client.project.tracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import client.project.tracker.domain.ProjectStatus
import client.project.tracker.domain.usecase.DeleteProjectUseCase
import client.project.tracker.domain.usecase.FilterProjectsUseCase
import client.project.tracker.domain.usecase.GetProjectsUseCase
import client.project.tracker.domain.usecase.SearchProjectsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProjects: GetProjectsUseCase,
    private val searchProjects: SearchProjectsUseCase,
    private val filterProjects: FilterProjectsUseCase,
    private val deleteProject: DeleteProjectUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            getProjects().collect { list ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        projects = list,
                        error = null
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Search -> {
                _uiState.update {
                    it.copy(searchQuery = event.query)
                }

                search(event.query)
            }

            is HomeEvent.Filter -> {
                _uiState.update {
                    it.copy(selectedStatus = event.status)
                }

                filter(ProjectStatus.valueOf(event.status.toString()))
            }

            is HomeEvent.Delete -> {
                delete(event.projectId)
            }

            HomeEvent.Refresh -> {
                loadProjects()
            }
        }
    }
    private fun delete(id: Long) {
        val project = _uiState.value.projects.find { it.id == id } ?: return
        viewModelScope.launch {
            deleteProject(project)
        }
    }

    private var searchJob: Job? = null

    private fun search(query: String) {

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            if (query.isBlank()) {

                getProjects().collect { projects ->

                    _uiState.update {

                        it.copy(projects = projects)

                    }

                }

            } else {

                searchProjects(query).collect { projects ->

                    _uiState.update {

                        it.copy(projects = projects)

                    }

                }

            }

        }

    }

    private fun filter(status: ProjectStatus){
        viewModelScope.launch {
            filterProjects(status).collect { list ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        projects = list,
                        error = null
                    )
                }
            }
        }
    }
}