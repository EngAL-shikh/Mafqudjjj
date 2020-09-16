package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //----------------logout------------------------------------

        logout.setOnClickListener {

            var shared= getSharedPreferences("idcheck",0)
            var edit=shared.edit()
            edit.putString("id",null)
            edit.commit()
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



        //------------------------profilesittings---------------------

        profileSittings.setOnClickListener {

            logout.visibility= View.VISIBLE


        }

        linearprofile.setOnClickListener {

            logout.visibility= View.GONE


        }

        //----------------------myPost---------------------------------

        mypostid.setOnClickListener {
            var intent=Intent(this,MyPosts::class.java)
            startActivity(intent)
        }

    }




}
