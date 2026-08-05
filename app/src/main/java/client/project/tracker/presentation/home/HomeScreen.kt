package client.project.tracker.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import client.project.tracker.domain.ProjectStatus
import client.project.tracker.presentation.components.EmptyView
import client.project.tracker.presentation.components.ErrorView
import client.project.tracker.presentation.components.LoadingView
import client.project.tracker.presentation.components.ProjectCard
import client.project.tracker.presentation.components.SearchBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit,
    onProjectClick: (Long) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Project Manager")
                }
            )

        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddClick
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Project"
                )

            }

        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SearchBar(
                value = uiState.searchQuery,
                onValueChange = {
                    viewModel.onEvent(HomeEvent.Search(it))
                }
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                item {

                    FilterChip(

                        selected = uiState.selectedStatus == null,

                        onClick = {
                            viewModel.onEvent(HomeEvent.Refresh)
                        },

                        label = {

                            Text("All")

                        }

                    )

                }

                ProjectStatus.entries.forEach { status ->

                    item {

                        FilterChip(

                            selected = uiState.selectedStatus == status.name,

                            onClick = {

                                viewModel.onEvent(HomeEvent.Filter(status.name))

                            },

                            label = {

                                Text(status.name.replace("_", " "))

                            }

                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            when {
                uiState.isLoading -> {
                    LoadingView()
                }

                uiState.error != null -> {

                    ErrorView(
                        message = uiState.error!!,
                        onRetry = {
                            viewModel.onEvent(HomeEvent.Refresh)
                        }
                    )

                }

                uiState.projects.isEmpty() -> {
                    EmptyView()
                }

                else -> {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) { items (
                            uiState.projects,
                            key = { it.id }
                        ) { project ->
                            ProjectCard(
                                project = project,
                                onClick = {
                                    onProjectClick(project.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}