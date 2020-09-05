package com.example.mafqud

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
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






        addnewpost_id.bringToFront()
        bar2.bringToFront()
















        // --------------------searching-----------------

        iv_search.setOnClickListener {

            ll_search.visibility=View.VISIBLE
            ll_home.visibility=View.GONE

        }

        iv_back_search.setOnClickListener {

            ll_search.visibility=View.GONE
            ll_home.visibility=View.VISIBLE

        }

//        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//
//
//                return true
//            }
//
//            override fun onQueryTextChange(txtsearch: String?): Boolean {
//
//                searchnewslist.clear()
//
//                for (i in posts){
//
//                    if (i.title.contains(txtsearch.toString())){
//
//                        searchnewslist.add(i)
//                    }
//
//
//                }
//                rv.layoutManager=
//                    LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,false)
//                var newsAdapter= postsadpter1(this@MainActivity,searchnewslist)
//                rv.adapter=ViewPagerAdapter
//
//
//
//
//
//
//                return true
//            }
//
//
//        })







       // var topic=FirebaseMessaging.getInstance().subscribeToTopic("posts")

        var viewpageradapter= ViewPagerAdapter(supportFragmentManager)
        viewpageradapter.addFragment(technologyFragment(),"الألكترونيات")


        viewpageradapter.addFragment(personFragment(),"الاشخاص")
        viewpageradapter.addFragment(Deco(),"ملفات")




        vp_whatsapp.adapter=viewpageradapter

        tab_whatsapp.setupWithViewPager(vp_whatsapp)

        addnewpost_id.setOnClickListener(){


            var shared= getSharedPreferences("sinup",0)
            var cheack=shared.getString("email",null)
            if (cheack!= null){

            var intent = Intent(this,addlost::class.java)
            startActivity(intent)
            }else{

                var intent = Intent(this,Login::class.java)
                startActivity(intent)
            }
        }



        //Toast.makeText(this,cheack, Toast.LENGTH_SHORT).show()







    }








}
