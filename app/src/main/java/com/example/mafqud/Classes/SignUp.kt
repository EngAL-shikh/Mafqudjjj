package com.example.mafqud

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_addlost.*
import kotlinx.android.synthetic.main.activity_addlost.name_id

import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        signup_id.setOnClickListener(){


            signup()
            var shared= getSharedPreferences("sinup",0)

            var edit=shared.edit()
            var em=emailcreat_id.text.toString()
            edit.putString("email",em)
            edit.commit()
           var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


    }



    fun signup(){



        var url="https://flexuous-babies.000webhostapp.com/adduser.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {




                response ->


            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="done"){


                Toast.makeText(this,"done", Toast.LENGTH_SHORT).show()



            } else{

                Toast.makeText(this,"not found", Toast.LENGTH_SHORT).show()}




        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

                var name=name_id.text.toString()
                var email=emailcreat_id.text.toString()
                var password=password_id.text.toString()





                paramas.put("name",name)
                paramas.put("email",email)
                paramas.put("password",password)








                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }
}
