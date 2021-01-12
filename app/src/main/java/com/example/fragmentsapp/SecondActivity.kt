package com.example.fragmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var str:String=intent.getStringExtra("name").toString()
        tvSecondActivity.text="Hey! ${str} You accepted all permissions"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_action_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tvSecondActivity.text=when(item.itemId){
            R.id.miSearch->"Search"
            R.id.miFavourite->"Favourite"
            R.id.miPersonAdd -> "Person Add"
            R.id.miSettings -> "Settings"
            R.id.miExit -> "Exit"
            else -> "You did't selected any one"
        }
        return true
    }
}