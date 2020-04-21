package com.example.graduationprojectkotlin

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.fragment)
        val configuration = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        //NavigationUI.setupActionBarWithNavController(this, navController, configuration)
        NavigationUI.setupWithNavController(toolbar, navController, configuration)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)


    }
}
