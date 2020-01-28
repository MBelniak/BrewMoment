package com.rubik.brewmoment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.rubik.brewmoment.ui.home.HomeFragment
import com.rubik.brewmoment.util.LoginUtil


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onStart() {
        super.onStart()
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val navMenu = navigationView.menu
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_my_results, R.id.nav_users_results, R.id.nav_my_recipes, R.id.nav_common_recipes,
                R.id.nav_users_recipes, R.id.nav_log_in, R.id.nav_account, R.id.nav_about
            ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)


        if (LoginUtil.isUserLoggedIn()) {
            navMenu.findItem(R.id.nav_log_in).isVisible = false
            navMenu.findItem(R.id.nav_account).isVisible = true
        } else {
            navMenu.findItem(R.id.nav_account).isVisible = false
            navMenu.findItem(R.id.nav_log_in).isVisible = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(homeIntent)
        }
        else
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

}
