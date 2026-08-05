package client.project.tracker.presentation.addedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import client.project.tracker.domain.Project
import client.project.tracker.domain.usecase.AddProjectUseCase
import client.project.tracker.domain.usecase.GetProjectByIdUseCase
import client.project.tracker.domain.usecase.UpdateProjectUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddEditProjectViewModel (
    private val addProject: AddProjectUseCase,
    private val updateProject: UpdateProjectUseCase,
    private val getProjectById: GetProjectByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditUiState())
    val state: StateFlow<AddEditUiState> = _state.asStateFlow()

    fun onEvent(event: AddEditEvent) {
        when (event) {

            is AddEditEvent.ClientNameChanged ->
                _state.update { it.copy(clientName = event.value) }

            is AddEditEvent.ProjectNameChanged ->
                _state.update { it.copy(projectName = event.value) }

            is AddEditEvent.DescriptionChanged ->
                _state.update { it.copy(description = event.value) }

            is AddEditEvent.StatusChanged ->
                _state.update { it.copy(status = event.value) }

            is AddEditEvent.PriorityChanged ->
                _state.update { it.copy(priority = event.value) }

            is AddEditEvent.StartDateChanged ->
                _state.update { it.copy(startDate = event.value) }

            is AddEditEvent.DueDateChanged ->
                _state.update { it.copy(dueDate = event.value) }

            AddEditEvent.Save -> save()
        }
    }

    private fun validate(state: AddEditUiState): String? {

        if (state.clientName.isBlank())
            return "Client name is required."

        if (state.projectName.isBlank())
            return "Project name is required."

//        if (state.dueDate.isBefore(state.startDate))
//            return "Due date must be after the start date."

        return null
    }

    private fun save() {
        val form = _state.value
        val error = validate(form)
        if (error != null) {
            _state.update {
                it.copy(error = error)
            }
            return
        }
        viewModelScope.launch {
            if (form.id == 0L) {
                addProject(
                    Project(
                    id = form.id,
                    clientName = form.clientName,
                    projectName = form.projectName,
                    description = form.description,
                    status = form.status,
                    priority = form.priority,
                    startDate = form.startDate,
                    dueDate = form.dueDate
                    )
                )

            } else {
                updateProject(
                    Project(
                        id = form.id,
                        clientName = form.clientName,
                        projectName = form.projectName,
                        description = form.description,
                        status = form.status,
                        priority = form.priority,
                        startDate = form.startDate,
                        dueDate = form.dueDate
                    )
                )
            }
        }
    }

    fun load(id: Long) {

        viewModelScope.launch {

            val project = getProjectById(id) ?: return@launch

            _state.value = AddEditUiState(

                id = project.id,

                clientName = project.clientName,

                projectName = project.projectName,

                description = project.description,

                status = project.status,

                priority = project.priority,

                startDate = project.startDate,

                dueDate = project.dueDate

            )

        }

    }
}