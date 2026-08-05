package client.project.tracker.presentation.settings

enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

enum class SortOrder {
    DUE_DATE,
    START_DATE,
    PRIORITY,
    PROJECT_NAME,
    CLIENT_NAME
}

enum class Environment {
    MOCK,
    PRODUCTION
}

data class SettingsUiState(

    val themeMode: ThemeMode = ThemeMode.SYSTEM,

    val notificationsEnabled: Boolean = true,

    val reminderDays: Int = 3,

    val sortOrder: SortOrder = SortOrder.DUE_DATE
)