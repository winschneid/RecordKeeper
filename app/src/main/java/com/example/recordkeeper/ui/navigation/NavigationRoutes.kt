package com.example.recordkeeper.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object LiveRecords : NavigationRoutes("live_records")
    object MovieRecords : NavigationRoutes("movie_records")
    object RamenRecords : NavigationRoutes("ramen_records")
    object AddLiveRecord : NavigationRoutes("add_live_record")
    object AddMovieRecord : NavigationRoutes("add_movie_record")
    object AddRamenRecord : NavigationRoutes("add_ramen_record")
}