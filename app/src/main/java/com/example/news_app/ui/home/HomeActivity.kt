package com.example.news_app.ui.home


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
        addFragment(CategoriesFragment())
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

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
        viewBinding.search.visibility = View.INVISIBLE
        viewBinding.toolbar.title = getString(R.string.news_app)


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
                    viewBinding.toolbar.title = "Settings"
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
        val v = fragment as CategoriesFragment
        v.onClickedItem = CategoriesFragment.OnClickedItem {
            viewBinding.toolbar.title = it
            viewBinding.search.isVisible = true

        }
    }
}
