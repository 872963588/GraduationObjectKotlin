package com.example.graduationprojectkotlin

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import androidx.appcompat.app.AppCompatActivity

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestions(this, MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
            }
        }
    }

    private fun doMySearch(query: String) {

    }
}
