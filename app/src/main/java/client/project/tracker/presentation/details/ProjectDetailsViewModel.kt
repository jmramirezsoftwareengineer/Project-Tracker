package client.project.tracker.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import client.project.tracker.domain.Project
import client.project.tracker.domain.usecase.DeleteProjectUseCase
import client.project.tracker.domain.usecase.GetProjectByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectDetailsViewModel(
    private val getProjectById: GetProjectByIdUseCase,
    private val deleteProject: DeleteProjectUseCase
) : ViewModel() {

    private val _project = MutableStateFlow<Project?>(null)

    val project: StateFlow<Project?> = _project

    fun load(id: Long) {
        viewModelScope.launch {
            _project.value = getProjectById(id)
        }
    }

    fun delete(){
        viewModelScope.launch {
            _project.value?.let { deleteProject(it) }
        }
    }

    fun update(id: Long) {
        viewModelScope.launch {
            _project.value = getProjectById(id)
        }
    }
}