package client.project.tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import client.project.tracker.presentation.addedit.AddEditProjectScreen
import client.project.tracker.presentation.details.ProjectDetailsScreen
import client.project.tracker.presentation.home.HomeScreen
import client.project.tracker.presentation.settings.SettingsScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                onAddClick = {
                    navController.navigate(
                        Screen.AddProject.route
                    )
                },

                onProjectClick = {
                    navController.navigate(
                        Screen.ProjectDetails.create(it)
                    )
                }
            )
        }

        composable(Screen.AddProject.route) {
            val id = it.arguments?.getLong("projectId") ?: 0

            AddEditProjectScreen(
                projectId = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            Screen.ProjectDetails.route,
            arguments = listOf(
                navArgument("projectId") {
                    type = NavType.LongType
                }
            )
        ) {

            val id = it.arguments?.getLong("projectId") ?: 0

            ProjectDetailsScreen(
                projectId = id,
                onBack = {
                    navController.popBackStack()
                },

                onEdit = { projectId ->
                    navController.navigate(
                        Screen.EditProject.create(projectId)
                    )
                }
            )
        }

        composable(
            Screen.EditProject.route,
            arguments = listOf(
                navArgument("projectId") {
                    type = NavType.LongType
                }
            )
        ) {

            val id = it.arguments?.getLong("projectId") ?: 0

            AddEditProjectScreen(
                projectId = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Settings.route) {

            SettingsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}