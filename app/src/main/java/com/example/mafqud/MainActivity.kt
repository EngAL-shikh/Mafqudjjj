package com.example.mafqud

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.p2p.WifiP2pManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apbarlayout.Post
import com.example.mafqud.R.color.selver
import com.example.mafqud.fragment.Deco
import com.example.mafqud.fragment.personFragment
import com.example.mafqud.fragment.technologyFragment
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_technology.*
import kotlinx.android.synthetic.main.posts.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.example.apbarlayout.postsadpter as postsadpter1
import com.example.mafqud.R.color.white as white1


class MainActivity : AppCompatActivity() {


    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cm= baseContext.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val network=cm.activeNetworkInfo
        if (network!=null && network.isConnected){
            Toast.makeText(this,"connected", Toast.LENGTH_SHORT).show()

        }else{
            imagecon.visibility=View.VISIBLE
        }
        addnewpost_id.bringToFront()
        bar2.bringToFront()

        //-----------------------------Profile---------------------

        bar2.setOnClickListener {
            var shared= getSharedPreferences("idcheck",0)
            var cheack=shared.getString("id",null)
            if (cheack!= null){

                var intent = Intent(this,Profile::class.java)
                startActivity(intent)
            }else{

                var intent = Intent(this,Login::class.java)
                startActivity(intent)
            }

        }


        // --------------------searching-----------------

        iv_search.setOnClickListener {

            ll_search.visibility=View.VISIBLE
            ll_home.visibility=View.GONE

        }

        iv_back_search.setOnClickListener {

            ll_search.visibility=View.GONE
            ll_home.visibility=View.VISIBLE

        }




        // -------------------------fragment------------------------
        var viewpageradapter= ViewPagerAdapter(supportFragmentManager)
        viewpageradapter.addFragment(technologyFragment(),"الألكترونيات")
        viewpageradapter.addFragment(personFragment(),"الاشخاص")
        viewpageradapter.addFragment(Deco(),"ملفات")
        vp_whatsapp.adapter=viewpageradapter
        tab_whatsapp.setupWithViewPager(vp_whatsapp)

        //-----------------------------addPosts-------------------------------
             addnewpost_id.setOnClickListener(){

                 var shared= getSharedPreferences("idcheck",0)
                 var cheack=shared.getString("id",null)
                 if (cheack!= null){

                     var intent = Intent(this,addlost::class.java)
                     startActivity(intent)
                 }else{

                     var intent = Intent(this,Login::class.java)
                     startActivity(intent)
                 }
                 getpermission()

                         }
    }


//------------------------------------Function-------------------------------------------
fun  getpermission(){

    if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.CAMERA
                ),1)
        }
    }
}


    fun chaeck(a:String){

        var shared= getSharedPreferences("idcheck",0)
        var cheack=shared.getString("id",null)
        if (cheack!= null){

            var intent = Intent(this,a::class.java)
            startActivity(intent)
        }else{

            var intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }





}
