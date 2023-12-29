package com.example.news_app.ui.home


import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.news_app.R
import com.example.news_app.databinding.ActivityHomeBinding
import com.example.news_app.ui.home.categoriesfragement.CategoriesFragment
import com.example.news_app.ui.home.settingsfragment.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    lateinit var toggel: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Darktheme)
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.lifecycleOwner = this
        initViews()
        addFragment(CategoriesFragment())
        executeOnBackPressed()


    }

    private fun executeOnBackPressed() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT)
            {
                supportFragmentManager.popBackStack()
                viewBinding.toolbar.title = getString(R.string.app_name)
            }
        } else {
            onBackPressedDispatcher.addCallback(this) {
                supportFragmentManager.popBackStack()
                viewBinding.toolbar.title = getString(R.string.app_name)
            }
        }
    }


    private fun initViews() {
        initDrawer()
        navigateDrawer()
        viewBinding.search.visibility = View.INVISIBLE
        viewBinding.search.setOnSearchClickListener {
            viewBinding.search.background = ContextCompat.getDrawable(this, R.drawable.searchback)
        }
        viewBinding.search.setOnCloseListener {
            viewBinding.search.background = null
            viewBinding.search.onActionViewCollapsed()
            return@setOnCloseListener true
        }

    }


    private fun initDrawer() {
        toggel = ActionBarDrawerToggle(
            this,
            viewBinding.drawer,
            viewBinding.toolbar,
            R.string.open,
            R.string.close
        )
        toggel.drawerArrowDrawable.color = resources.getColor(R.color.white)
        viewBinding.drawer.addDrawerListener(toggel)
        toggel.syncState()
    }

    private fun navigateDrawer() {
        viewBinding.nav.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.cate -> {
                    addFragment(CategoriesFragment())
                    viewBinding.toolbar.title = getString(R.string.news_app)
                    viewBinding.drawer.close()
                }

                R.id.settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingsFragment()).addToBackStack("").commit()
                    viewBinding.toolbar.title = getString(R.string.settings)
                    viewBinding.drawer.close()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggel.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
