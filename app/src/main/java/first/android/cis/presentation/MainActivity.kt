package first.android.cis.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import first.android.cis.R
import com.example.data.storage.sharedpref.SharedPrefTokensStorage
import com.example.data.tokensRepository.TokensRepositoryImpl
import com.cis.domain.usecases.signInUp.GetTokens
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tokenStorage by lazy { SharedPrefTokensStorage(this.applicationContext) }
    private val tokensRepository by lazy { TokensRepositoryImpl(tokensStorage = tokenStorage) }
    private val getTokens by lazy{ GetTokens(tokensRepository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.navView)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_news,
            R.id.navigation_calendar,
            R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setStartDestination()
    }

    fun setStartDestination() {
        val navController = findNavController(R.id.nav_host_fragment)
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.mobile_navigation)
        val accessToken: String = getTokens.execute().accessToken
        if ((accessToken == "Bearer ") or (accessToken == "")){
            navGraph.startDestination = R.id.regAuthFragment
            navController.graph = navGraph
            navView.visibility = View.GONE
        } else {
            navGraph.startDestination = R.id.navigation_news
            navController.graph = navGraph
            navView.visibility = View.VISIBLE
        }
    }
}


