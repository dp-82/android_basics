package com.example.fragmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val home=HomeFragment()
        val message=MessageFragment()
        val profile=ProfileFragment()
        updateFrameLayout(home)
        bnvBottomNav.setOnNavigationItemSelectedListener {
            updateFrameLayout(
                when(it.itemId){
                    R.id.bnvHome -> home
                    R.id.bnvProfile -> profile
                    R.id.bnvMessage -> message
                    else -> home
                }
            )
            true
        }
    }
    private fun updateFrameLayout(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flBottomNav,fragment)
            commit()
        }
    }
}