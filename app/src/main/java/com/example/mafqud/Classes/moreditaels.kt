package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_moreditaels.*
import kotlinx.android.synthetic.main.posts.*

class moreditaels : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moreditaels)


        idmore.text= intent.extras!!.getString("post").toString()
        var title=intent.extras!!.getString("title").toString()
        var loc=intent.extras!!.getString("location")



        showlocation.setOnClickListener{

            var intent=Intent(this,MapsActivity::class.java)
            var map =2.toString()
            intent.putExtra("map",map)
            intent.putExtra("location",loc)
            intent.putExtra("title",title)
            startActivity(intent)
        }


    }
}
