package client.project.tracker.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object AddProject : Screen("add_project")

    data object EditProject : Screen("edit_project/{projectId}") {

        fun create(projectId: Long) =
            "edit_project/$projectId"

    }

    data object ProjectDetails : Screen("project_details/{projectId}") {

        fun create(projectId: Long) =
            "project_details/$projectId"

    }

    data object Settings : Screen("settings")

}