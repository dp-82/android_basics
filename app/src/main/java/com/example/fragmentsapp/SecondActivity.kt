package com.example.fragmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var str: String = intent.getStringExtra("name").toString()
        tvSecondActivity.text = "Hey! ${str} You accepted all permissions"

        val firstDialog = AlertDialog.Builder(this)
            .setTitle("Contact Add")
            .setMessage("Whether you want to add dp to your contact list?")
            .setIcon(R.drawable.ic_person_add)
            .setPositiveButton("Add") { _, _ ->
                Toast.makeText(this, "Hey! you added!!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, "Hey! you cancelled!!", Toast.LENGTH_SHORT).show()
            }.create()

        val genderList=arrayOf("Male", "Female")
        val secondDailog = AlertDialog.Builder(this)
            .setTitle("Gender")
            .setMessage("Choose Your Gender")
            .setIcon(R.drawable.ic_gender)
            .setSingleChoiceItems(genderList, 0) { dialogInterface, which ->
                Toast.makeText(this, "You choosed ${genderList[which]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Add") { _, _ ->
                Toast.makeText(this, "Hey! you added!!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, "Hey! you cancelled!!", Toast.LENGTH_SHORT).show()
            }.create()

        dailogFirst.setOnClickListener {
            firstDialog.show()
        }
        dailogSecond.setOnClickListener {
            secondDailog.show()
        }

        val monthsList= arrayListOf<String>("January","Feb","Mar")
        val adapter= ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,monthsList)
        spMonths.adapter=adapter

        spMonths.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SecondActivity,"You selected ${parent?.getItemAtPosition(position).toString()} ",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@SecondActivity,"Hey you clicked nothing",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tvSecondActivity.text = when (item.itemId) {
            R.id.miSearch -> "Search"
            R.id.miFavourite -> "Favourite"
            R.id.miPersonAdd -> "Person Add"
            R.id.miSettings -> "Settings"
            R.id.miExit -> "Exit"
            else -> "You did't selected any one"
        }
        return true
    }
}