package org.hakandindis.financeapp.scene.main

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val IS_REMEMBER = "is_remember"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setAuthState()
        setSupportActionBar(binding.activityMainToolbar)
    }

    override fun onStart() {
        super.onStart()
        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.fragmentContainerView)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setAuthState() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val isRememberUser = sharedPref.getBoolean(IS_REMEMBER, false)
        if (Firebase.auth.currentUser != null && isRememberUser) {
            viewModel.isAuthenticated.value = true
        } else {
            if (Firebase.auth.currentUser != null) {
                Firebase.auth.signOut()
            }
            viewModel.isAuthenticated.value = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let { menuInflater.inflate(R.menu.main_activity_toolbar_menu, menu) }

        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_activity_toolbar_forget_account -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Çıkış yapmak istediğinize emin misiniz?")
                    .setMessage("Daha sonrasında tekrar giriş yapmak zorunda kalacaksınız")
                    .setNegativeButton("Hayır", null)
                    .setPositiveButton("Evet") { _, _ ->
                        viewModel.isAuthenticated.value = false
                        Firebase.auth.signOut()
                        findNavController(R.id.fragmentContainerView).navigate(R.id.action_global_mainFragment)
                    }
                    .show()

                return true
            }
        }

        return false
    }
}