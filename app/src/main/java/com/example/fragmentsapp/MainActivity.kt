package com.example.fragmentsapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dummy.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*

class MainActivity : AppCompatActivity() {
    private var fragmentFlag:Boolean=true
    private val first = FirstFragment()
    private val second = SecondFragment()
    val dummy=DummyFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                if(fragmentFlag){
                    replace(R.id.frameLayout,first)
                }else{
                    replace(R.id.frameLayout, second)
                }
                commit()
            }
            fragmentFlag=!fragmentFlag
        }
        ivChoosen.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type="image/*"
                startActivityForResult(it,0)
            }
        }
        requestPermission.setOnClickListener {
            requestAllPermissions()
        }
    }

    private fun hasInternet() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasExternalStorage() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestAllPermissions() {
        Log.d("requestAllPermissions", "called")
        val reqPerm = mutableListOf<String>()
//        if (!hasInternet()) reqPerm.add(Manifest.permission.INTERNET)
        if (!hasExternalStorage()) reqPerm.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (reqPerm.isNotEmpty()) {
            Log.d("if->", "called")
            ActivityCompat.requestPermissions(this,reqPerm.toTypedArray(), 0)
        } else {
            startSecondActivity()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,R.string.you_need_to_accept_all_permissions_to_go_to_second_activity,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startSecondActivity() {
        Intent(this, SecondActivity::class.java).also {
            it.putExtra("name","Dharma Raj")
            startActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode==0){
            val uri=data?.data
            ivChoosen.setImageURI(uri)
//            ivDummy.setImageResource(R.drawable.ic_launcher_background)
//            ivDummy.setImageURI(uri)
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.frameLayout,DummyFragment())
//                commit()
//            }
        }
    }
}