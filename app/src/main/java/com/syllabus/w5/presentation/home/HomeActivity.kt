package com.syllabus.w5.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.syllabus.w5.R
import com.syllabus.w5.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.lang.System.gc
import java.util.*
import com.squareup.moshi.Moshi
import com.syllabus.w5.repository.remote.dominos.server.DominosRetrofitService
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeActivity : AppCompatActivity() {

    private var isLocationEnabled: Boolean = false;
    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableLocation()
        bindActions()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://order.dominos.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(DominosRetrofitService::class.java)

        lifecycleScope.launch {
            val response = service.search("26870")
        }
    }

    @SuppressLint("MissingPermission")
    private fun bindActions() {
        binding.getLastLocationBtn.setOnClickListener { btn ->
            binding.getLastLocationBtn.isEnabled = false
            lifecycleScope.launch {
                val client = LocationServices.getFusedLocationProviderClient(this@HomeActivity)
                Timber.d("Retrieving last location")
                client.lastLocation.addOnCompleteListener {
                    it.result?.let {
                        Toast.makeText(this@HomeActivity, it.toString(), Toast.LENGTH_LONG).show()
                    }
                    binding.getLastLocationBtn.isEnabled = true
                }
            }
        }
        binding.getCurrentLocationBtn.setOnClickListener { btn ->
            binding.getCurrentLocationBtn.isEnabled = false
            lifecycleScope.launch {
                try {
                    Timber.d("Requesting Location Manager")
                    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5000,
                        5f
                    ) { location ->
                        Timber.d("Current location requested successfully")
                        Toast.makeText(
                            this@HomeActivity,
                            location.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    binding.getCurrentLocationBtn.isEnabled = true
                    Timber.e(e)
                }
            }
        }
        binding.getCoordsBtn.setOnClickListener { btn ->
            binding.getCoordsBtn.isEnabled = false
            lifecycleScope.launch {
                try {
                    val gc = Geocoder(this@HomeActivity)
                    val list = gc.getFromLocationName(binding.inputTvw.text.toString(), 1)
                    if (list.isNotEmpty()) {
                        val address = list[0]
                        Toast.makeText(this@HomeActivity, address.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }
                binding.getCoordsBtn.isEnabled = true
            }
        }
        binding.getGeocodeBtn.setOnClickListener { btn ->
            binding.getGeocodeBtn.isEnabled = false
            lifecycleScope.launch {
                try {
                    val coords = binding.inputTvw.text.toString().split(",")
                    val latitude = coords[0].toDouble()
                    val longitude = coords[1].toDouble()
                    val gc = Geocoder(this@HomeActivity)
                    val list = gc.getFromLocation(latitude, longitude, 1)
                    if (list.isNotEmpty()) {
                        val address = list[0]
                        Toast.makeText(this@HomeActivity, address.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }
                binding.getGeocodeBtn.isEnabled = true
            }
        }
        Timber.d("Home activity created")
    }

    private fun enableLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            isLocationEnabled = true
            Timber.d("Location permissions are already granted")
        } else {
            Timber.d("Location permissions are not granted")
            Timber.d("Trying to get location permissions")
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage("We need acces to your location")
            .setPositiveButton("Ok") { dialog, which ->
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        builder.create().show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val results = permissions.zip(grantResults.toList()).toMap()
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (results[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED
                    || results[Manifest.permission.ACCESS_COARSE_LOCATION] == PackageManager.PERMISSION_GRANTED
                ) {
                    isLocationEnabled = true
                    Timber.d("Location permissions were granted")
                } else {
                    Timber.d("Location permissions were not granted")
                }
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

}