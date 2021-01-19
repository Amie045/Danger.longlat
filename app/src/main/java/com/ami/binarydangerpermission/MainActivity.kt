package com.ami.binarydangerpermission

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var tv_longlat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_check_permission = findViewById<Button>(R.id.btn_check_permission)

        tv_longlat = findViewById(R.id.tv_longlat)

        btn_check_permission.setOnClickListener {
            val permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)

            if(permissionCheck == PackageManager.PERMISSION_GRANTED){

                getLonglat()
            }else{

                requestPermission()

            }


        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        when(requestCode){
            201 -> {
                if (permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLonglat()

                }else{
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun requestPermission(){
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 201)
    }

    @SuppressLint("MissingPermission")
    fun getLonglat(){
        val locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        tv_longlat.setText("Lat : ${location?.latitude} Long : ${location?.longitude} ")


    }
}