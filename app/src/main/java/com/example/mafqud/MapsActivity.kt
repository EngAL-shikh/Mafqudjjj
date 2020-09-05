package com.example.mafqud

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_addlost.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_moreditaels.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

         var map =intent.extras!!.getString("map").toString()

        if (map=="1"){


            mMap = googleMap
            var newlocation=LatLng(15.471558, 44.226781)
            // Add a marker in Sydney and move the camera

            //mMap.mapType.
            mMap.addMarker(MarkerOptions().position(newlocation).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlocation, 20F))
            mMap.setOnMapClickListener(object :GoogleMap.OnMapClickListener {
                override fun onMapClick(latlng :LatLng) {
                    val location1 = LatLng(latlng.latitude,latlng.longitude)
                    //var looo= arrayOf(latlng.latitude,",",latlng.longitude)

                   var location=latlng.latitude.toString()+","+latlng.longitude.toString()
                    val sydney = LatLng(latlng.latitude, latlng.longitude)
                    Toast.makeText(this@MapsActivity,location.toString(),Toast.LENGTH_SHORT).show()
                    var loc=Intent()
                    loc.putExtra("location",location.toString())
                    mMap.clear()
                    mMap.addMarker(MarkerOptions().position(location1))

                    setResult(Activity.RESULT_OK,loc)
                    finish()


                }
            })
        }else{
            var title=intent.extras!!.getString("title")
            var loc =intent.extras!!.getString("location")
            val latlong = loc!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val latitude = java.lang.Double.parseDouble(latlong[0])
            val longitude = java.lang.Double.parseDouble(latlong[1])


            mMap = googleMap
           // mMap.mapType(GoogleMap.MAP_TYPE_SATELLITE)
             mMap.uiSettings.isZoomControlsEnabled=true
            mMap.uiSettings.isMyLocationButtonEnabled=true
            var newlocation=LatLng(latitude,longitude)
            mMap.addMarker(MarkerOptions().position(newlocation).title(title)).showInfoWindow()
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlocation, 20F))
        }


    }
}
