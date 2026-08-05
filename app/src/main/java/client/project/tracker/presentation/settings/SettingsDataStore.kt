package client.project.tracker.presentation.settings

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore(
    private val context: Context
) {

    companion object {

        private val THEME =
            stringPreferencesKey("theme")

        private val SORT =
            stringPreferencesKey("sort")

        private val NOTIFICATION =
            booleanPreferencesKey("notification")

        private val REMINDER =
            intPreferencesKey("reminder")
    }

    val settings: Flow<SettingsUiState> =
        context.dataStore.data.map { pref ->

            SettingsUiState(

                themeMode = ThemeMode.valueOf(
                    pref[THEME] ?: ThemeMode.SYSTEM.name
                ),

                notificationsEnabled =
                    pref[NOTIFICATION] ?: true,

                reminderDays =
                    pref[REMINDER] ?: 3,

                sortOrder = SortOrder.valueOf(
                    pref[SORT] ?: SortOrder.DUE_DATE.name
                )

            )

        }

    suspend fun setTheme(mode: ThemeMode) {

        context.dataStore.edit {

            it[THEME] = mode.name

        }

    }

    suspend fun setSortOrder(order: SortOrder) {

        context.dataStore.edit {

            it[SORT] = order.name

        }

    }

    suspend fun setReminder(days: Int) {

        context.dataStore.edit {

            it[REMINDER] = days

        }

    }

    suspend fun setNotification(enabled: Boolean) {

        context.dataStore.edit {

            it[NOTIFICATION] = enabled

        }

    }

}