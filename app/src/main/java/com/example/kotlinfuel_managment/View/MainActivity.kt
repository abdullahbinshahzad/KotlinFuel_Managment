package com.example.kotlinfuel_managment.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.ActivityMainBinding
import com.example.kotlinfuel_managment.View.fragments.BarChartFragment
import com.example.kotlinfuel_managment.View.fragments.HistoryFragment
import com.example.kotlinfuel_managment.View.fragments.HomeFragment
import com.example.kotlinfuel_managment.View.fragments.PieChartFragment
import com.example.kotlinfuel_managment.hideKeyboard
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.history -> {
                    loadFragment(HistoryFragment())
                    true
                }

                R.id.bar_chart -> {
                    loadFragment(BarChartFragment())
                    true
                }

                R.id.pie_chart -> {
                    loadFragment(PieChartFragment())
                    true
                }
            }
            true
        }
//        SHOW YOUR APP NAME ON THE ACTION BAR        ////////////////
//        setSupportActionBar(binding.toolbar)

//        NAVIGATION DRAWER        ////////////////
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.drawerNavigation.setNavigationItemSelectedListener(this)

    }
//   LOAD FRAGMENT ON BOTTOM NAVIGATION CLICK        ////////////////

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

//   NAVIGATION DRAWER        ////////////////

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.home ->{Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show()}
            R.id.history ->{Toast.makeText(this, "HISTORY", Toast.LENGTH_SHORT).show()}
            R.id.pie_chart ->{Toast.makeText(this, "PIE CHART", Toast.LENGTH_SHORT).show()}
            R.id.bar_chart ->{Toast.makeText(this, "BAR CHART", Toast.LENGTH_SHORT).show()}
        }
      //  binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
    private fun openFragment(fragment: Fragment){
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

//class MainActivity : AppCompatActivity() {
//    //Add a reference to your Room database
//    private lateinit var database: FuelEntryDatabase
//
//     override fun onCreate(savedInstanceState: Bundle?) {

//        // Initialize the database
//        database = FuelEntryDatabase.getInstance(this)
//
//        addFuelButton.setOnClickListener {
//            // ... (Your existing code to add fuel)
//
//            // Add a new fuel entry to the database
//            val fuelEntry = FuelEntry(0, fuel, totalDistance, totalFuel)
//            GlobalScope.launch {
//                withContext(Dispatchers.IO) {
//                    database.fuelEntryDao().insertFuelEntry(fuelEntry)
//                }
//            }
//        }
//
//        calculateConsumptionButton.setOnClickListener {
//
//            }
//        }
//    }
//
//    // ... (Rest of your existing code)
//
//    private fun calculateAndDisplayConsumption() {
//        if (totalDistance > 0 && totalFuel > 0) {
//            val consumption = totalDistance / totalFuel
//            val df = DecimalFormat("#.##")
//            consumptionTextView.text = "Consumption: ${df.format(consumption)} miles/gallon or km/l"
//        }
//    }
//}
