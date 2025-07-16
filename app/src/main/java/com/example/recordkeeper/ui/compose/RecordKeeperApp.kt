package com.example.recordkeeper.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recordkeeper.R
import com.example.recordkeeper.ui.navigation.NavigationRoutes
import com.example.recordkeeper.ui.screens.AddLiveRecordScreen
import com.example.recordkeeper.ui.screens.AddMovieRecordScreen
import com.example.recordkeeper.ui.screens.AddRamenRecordScreen
import com.example.recordkeeper.ui.theme.RecordKeeperTheme
import com.example.recordkeeper.viewmodel.RecordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordKeeperApp(viewModel: RecordViewModel) {
    val navController = rememberNavController()
    val selectedTab by viewModel.selectedTab.collectAsState()
    
    val tabs = listOf(
        stringResource(R.string.live_tab),
        stringResource(R.string.movie_tab),
        stringResource(R.string.ramen_tab)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (selectedTab) {
                        0 -> navController.navigate(NavigationRoutes.AddLiveRecord.route)
                        1 -> navController.navigate(NavigationRoutes.AddMovieRecord.route)
                        2 -> navController.navigate(NavigationRoutes.AddRamenRecord.route)
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_record))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = selectedTab
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { viewModel.setSelectedTab(index) },
                        text = { Text(title) }
                    )
                }
            }
            
            Box(modifier = Modifier.fillMaxSize()) {
                RecordNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    selectedTab = selectedTab
                )
            }
        }
    }
}

@Composable
fun RecordNavHost(
    navController: NavHostController,
    viewModel: RecordViewModel,
    selectedTab: Int
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            when (selectedTab) {
                0 -> LiveRecordsScreen(viewModel = viewModel)
                1 -> MovieRecordsScreen(viewModel = viewModel)
                2 -> RamenRecordsScreen(viewModel = viewModel)
            }
        }
        composable(NavigationRoutes.AddLiveRecord.route) {
            AddLiveRecordScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(NavigationRoutes.AddMovieRecord.route) {
            AddMovieRecordScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(NavigationRoutes.AddRamenRecord.route) {
            AddRamenRecordScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecordKeeperAppPreview() {
    RecordKeeperTheme {
        // Preview用のダミーViewModel
        // 実際のプレビューではViewModelなしで表示
        RecordKeeperAppPreviewContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecordKeeperAppPreviewContent() {
    val tabs = listOf("ライブ", "映画", "ラーメン")
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "記録キーパー")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(Icons.Default.Add, contentDescription = "記録を追加")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = selectedTab
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "${tabs[selectedTab]}の記録一覧",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}