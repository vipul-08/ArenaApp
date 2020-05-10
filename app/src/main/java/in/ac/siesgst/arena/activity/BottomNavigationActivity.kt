package `in`.ac.siesgst.arena.activity

import `in`.ac.siesgst.arena.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onStart() {
        super.onStart()
        sharedPreferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("username", "null") == "null") {
            Intent(applicationContext, LauncherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.contests_nav_item, R.id.problem_set_nav_item, R.id.profile_nav_item
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
