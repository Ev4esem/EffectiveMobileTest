package com.dagteam.effectivemobiletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dagteam.auth_api.AuthPresentationApi
import com.dagteam.effectivemobiletest.ui.presentation.AccountPresentationLauncher
import com.dagteam.effectivemobiletest.ui.presentation.BottomNavItem
import com.dagteam.effectivemobiletest.ui.theme.EffectiveMobileTestTheme
import com.dagteam.favourite_api.FavouritePresentationApi
import com.dagteam.favourite_api.FavouritePresentationLauncher
import com.dagteam.main_api.MainPresentationApi
import com.dagteam.main_api.MainPresentationLauncher
import com.dagteam.splash_api.SplashPresentationApi
import com.dagteam.splash_api.SplashPresentationLauncher
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import com.dagteam.resources.R as Resources

class MainActivity : ComponentActivity(), KoinComponent {

    private val splashPresentationApi by inject<SplashPresentationApi>()
    private val authPresentationApi by inject<AuthPresentationApi>()
    private val mainPresentationApi by inject<MainPresentationApi>()
    private val favouritePresentationApi by inject<FavouritePresentationApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EffectiveMobileTestTheme(darkTheme = true) {
                MainApp()
            }
        }
    }

    @Composable
    private fun MainApp() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val showBottomBar = when (currentDestination?.route) {
            MainPresentationLauncher::class.java.name,
            FavouritePresentationLauncher::class.java.name,
            AccountPresentationLauncher::class.java.name -> true
            else -> false
        }

        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = SplashPresentationLauncher,
                modifier = Modifier.padding(innerPadding),
                builder = {
                    splashPresentationApi.registerGraph(this, navController)
                    authPresentationApi.registerGraph(this, navController)
                    mainPresentationApi.registerGraph(this, navController)
                    favouritePresentationApi.registerGraph(this, navController)
                    fakeAccountScreen(this)
                }
            )
        }
    }

    private fun fakeAccountScreen(
        navGraphBuilder: NavGraphBuilder,
    ) {
        navGraphBuilder.composable<AccountPresentationLauncher> {}
    }

    @Composable
    private fun BottomNavigationBar(
        navController: NavHostController,
        currentDestination: NavDestination?
    ) {
        val items = listOf(
            BottomNavItem(
                route = MainPresentationLauncher::class.java.name,
                iconResId = Resources.drawable.ic_house,
                labelResId = Resources.string.bottom_nav_bar_main,
            ),
            BottomNavItem(
                route = FavouritePresentationLauncher::class.java.name,
                iconResId = Resources.drawable.ic_bookmark,
                labelResId = Resources.string.bottom_nav_bar_favourite,
            ),
            BottomNavItem(
                route = AccountPresentationLauncher::class.java.name,
                iconResId = Resources.drawable.ic_person,
                labelResId = Resources.string.bottom_nav_bar_account,
            ),
        )

        NavigationBar {
            items.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.iconResId),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(item.labelResId)) }
                )
            }
        }
    }
}