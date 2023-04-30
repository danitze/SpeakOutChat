package com.coursework.speakoutchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        routeFirstScreen(savedInstanceState)
    }

    private fun routeFirstScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            showFirstScreen()
        }
    }

    private fun showFirstScreen() {
        val deepLink = "android-app://com.coursework.speakoutchat/auth_login"
        navHostFragment.navController.navigate(
            deepLink.toUri(),
            NavOptions.Builder().setPopUpTo(
                com.coursework.speakoutchat.auth_ui.R.id.fragment_login, true
            ).build()
        )
    }
}