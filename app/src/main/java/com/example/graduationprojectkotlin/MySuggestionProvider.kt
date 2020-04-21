package com.example.graduationprojectkotlin

import android.content.SearchRecentSuggestionsProvider

class MySuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.example.graduationprojectkotlin.MySuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}