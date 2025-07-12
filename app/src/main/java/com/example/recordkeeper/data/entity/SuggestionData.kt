package com.example.recordkeeper.data.entity

data class ArtistSuggestion(
    val artist: String,
    val count: Int,
    val latest_date: String
)

data class VenueSuggestion(
    val venue: String,
    val count: Int,
    val latest_date: String
)

data class TheaterSuggestion(
    val theater: String,
    val count: Int,
    val latest_date: String
)

data class ShopSuggestion(
    val shopName: String,
    val count: Int,
    val latest_date: String
)

data class MenuSuggestion(
    val menuName: String,
    val count: Int,
    val latest_date: String
)