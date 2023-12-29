package com.main.songfinder

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

import com.main.songfinder.SongFinderApplication.Companion.context
import com.main.songfinder.ui.search.SearchActivity

/**
 *  Questi classe si occupa dell'activity principale
 *  @author umbertodomenicociccia
 * */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        val navView: NavigationView = findViewById(R.id.navView)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        navView.setCheckedItem(R.id.findSong)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.findMe -> {
                    val intent=Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(SongFinderApplication.FINDMEURL)
                    )
                    startActivity(intent)
                    drawerLayout.closeDrawers()
                }

                R.id.findSong -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawers()
                }

                R.id.mailMe -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "message/rfc822"
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("umbertociccia@icloud.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Oggetto dell'email")
                    intent.putExtra(Intent.EXTRA_TEXT, "Corpo dell'email")
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        val data: SharedPreferences = context.getSharedPreferences("song_finder", MODE_PRIVATE)
        data.edit().clear().apply()
        Log.d("MainActivity", "cleared data")
    }

}