package com.example.group6_mapd711_assignment4

import android.app.ProgressDialog.show
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainMenuActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val shared =  getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val intent = Intent(this, MainActivity::class.java)
        if(shared?.getString("UserName", "")?.isEmpty() == true)
        {
            startActivity(intent)
        }
        else {
            val draweLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            val navView = findViewById<NavigationView>(R.id.nav_view)
            val headerView = navView.getHeaderView(0)
            var navUserName = headerView.findViewById<TextView>(R.id.user_name)
            var navEmail = headerView.findViewById<TextView>(R.id.user_email)

            navUserName.text ="Welcome " + shared?.getString("FirstName", "") + " "+ shared?.getString("LastName", "")
            navEmail.text = shared?.getString("Email", "")

            toggle = ActionBarDrawerToggle(this, draweLayout, R.string.open, R.string.close)
            draweLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home ->
                    {
                        loadFragment(HomeFragment())
                        draweLayout.closeDrawers()
                    }
                    R.id.nav_Logout -> {
                        val preferences = getSharedPreferences("UserProfile", 0)
                        preferences.edit().remove("UserName").commit()
                        startActivity(intent)
                    }
                    R.id.nav_editCustomer -> {
                        loadFragment(EditCustomerFragment())
                        draweLayout.closeDrawers()
                    }
                    R.id.nav_cruise ->
                    {
                        loadFragment(CruiseTypeFragment())
                        draweLayout.closeDrawers()
                    }
                    R.id.nav_bookingList -> {
                        loadFragment(ShowBookingsFragment())
                        draweLayout.closeDrawers()
                    }
                    R.id.nav_Logger -> {
                        loadFragment(LoggerFragment())
                        draweLayout.closeDrawers()
                    }

                }
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun goToEditBookings()
    {
        loadFragment(EditBookingFragment())
    }
    fun goToPackages()
    {
     loadFragment(PackagesFragment())

    }
    fun goToPackageDetailFragment()
    {
        loadFragment(PackageDetailFragment())

    }
    fun goToConfirmFragment()
    {
        loadFragment(ConfirmInputInformationFragment())
    }
    fun goToPaymentTypeFragment()
    {
        loadFragment(PaymentOptionsFragment())
    }
    fun goToPaymentFragment()
    {
        loadFragment(CreditPayFragment())
    }
    fun goToFinalBookingFragment()
    {
        loadFragment(FinalBookingFragment())
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.menu_ConstraintLayout, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}