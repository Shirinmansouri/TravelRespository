package com.example.group6_mapd711_assignment4

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainMenuActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val draweLayout= findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,draweLayout,R.string.open,R.string.close)
        draweLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.nav_home ->  Toast.makeText( applicationContext,"Home",Toast.LENGTH_LONG).show()
                R.id.nav_Logout ->  Toast.makeText( applicationContext,"LogOut",Toast.LENGTH_LONG).show()
                R.id.nav_editCustomer ->  Toast.makeText( applicationContext,"EditCustomer",Toast.LENGTH_LONG).show()
                R.id.nav_cruise ->  Toast.makeText( applicationContext,"cruise",Toast.LENGTH_LONG).show()
                R.id.nav_bookingList ->  Toast.makeText( applicationContext,"bookingList",Toast.LENGTH_LONG).show()

            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}