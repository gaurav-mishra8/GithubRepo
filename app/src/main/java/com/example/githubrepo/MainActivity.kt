package com.example.githubrepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val userManager = UserManager()

        if (!userManager.isUserLoggedIn) {
            val action = TrendingRepoFragmentDirections.actionTrendingRepoToLoginFragment()
            findNavController(R.id.nav_host_fragment).navigate(action)
        }

    }
}
