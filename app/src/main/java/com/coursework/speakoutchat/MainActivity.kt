package com.coursework.speakoutchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.coursework.speakoutchat.auth_domain_data.use_case.UserExistsUseCase
import com.coursework.speakoutchat.chat_ui.NavigateToMenuEventApi
import com.coursework.speakoutchat.menu_ui.menu.NavigateToLoginEventApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userExistsUseCase: UserExistsUseCase

    @Inject
    lateinit var navigateToLoginEventApi: NavigateToLoginEventApi

    @Inject
    lateinit var navigateToMenuEventApi: NavigateToMenuEventApi

    private val navHostFragment: NavHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        routeFirstScreen(savedInstanceState)
        observeNavigationEvents()
    }

    private fun routeFirstScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            showFirstScreen()
        }
    }

    private fun showFirstScreen() {
        lifecycleScope.launch {
            val deepLink = if (userExistsUseCase.checkUserExists()) {
                "android-app://com.coursework.speakoutchat/menu"
            } else {
                "android-app://com.coursework.speakoutchat/auth_login"
            }
            navHostFragment.navController.navigate(
                deepLink.toUri(),
                NavOptions.Builder().setPopUpTo(
                    com.coursework.speakoutchat.auth_ui.R.id.fragment_login, true
                ).build()
            )
        }
    }

    private fun navigateToLogin() {
        navHostFragment.navController.navigate(
            "android-app://com.coursework.speakoutchat/auth_login".toUri(),
            NavOptions.Builder().setPopUpTo(
                com.coursework.speakoutchat.menu_ui.R.id.fragment_menu, true
            ).build()
        )
    }

    private fun navigateToMenu() {
        navHostFragment.navController.navigate(
            "android-app://com.coursework.speakoutchat/menu".toUri(),
            NavOptions.Builder().setPopUpTo(
                com.coursework.speakoutchat.chat_ui.R.id.fragment_chat, true
            ).build()
        )
    }

    private fun observeNavigationEvents() {
        lifecycleScope.launch(CoroutineName("Observe navigation events")) {
            navigateToLoginEventApi.navigateToLoginEvent.filterNotNull().onEach {
                navigateToLogin()
                navigateToLoginEventApi.navigateToLoginEventConsumed()
            }.launchIn(this)

            navigateToMenuEventApi.navigateToMenuEvent.filterNotNull().onEach {
                navigateToMenu()
                navigateToMenuEventApi.navigateToMenuEventConsumed()
            }.launchIn(this)
        }
    }
}