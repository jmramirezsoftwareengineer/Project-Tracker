package client.project.tracker.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStore: SettingsDataStore
) : ViewModel() {

    val uiState =
        dataStore.settings.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SettingsUiState()
        )

    fun updateTheme(mode: ThemeMode) {

        viewModelScope.launch {

            dataStore.setTheme(mode)

        }

    }

    fun updateSort(order: SortOrder) {

        viewModelScope.launch {

            dataStore.setSortOrder(order)

        }

    }

    fun updateReminder(days: Int) {

        viewModelScope.launch {

            dataStore.setReminder(days)

        }

    }

    fun updateNotification(enabled: Boolean) {

        viewModelScope.launch {

            dataStore.setNotification(enabled)

        }

    }

}