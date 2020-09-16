package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUp : AppCompatActivity() {
    var convert=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //=================== i have acount==========================

        Ihave_acount.setOnClickListener {

            var intent=Intent(this,Login::class.java)
            startActivity(intent)
        }

//==========================singup===================================

        signup_id.setOnClickListener(){


            signup()
           // Toast.makeText(this,"تم التسجيل بنجاح  قم بتسجيل الدخول",Toast.LENGTH_SHORT).show()





        }


    }



    fun signup(){


        var url= "http://192.168.1.3/mafqud/adduser.php"
        //var url="https://flexuous-babies.000webhostapp.com/adduser.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {




                response ->


            var jsonobject= JSONObject(response)


                if (jsonobject.getString("msg") == "emptyname") {
                    Toast.makeText(this, "يجب كتابة الاسم ", Toast.LENGTH_SHORT).show()
                    user_id.setBackgroundResource(R.drawable.error)
                }else{
                    user_id.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "emptyemail") {

                    Toast.makeText(this, "الايميل غير صحيح ", Toast.LENGTH_SHORT).show()
                    emailcreat_id.setBackgroundResource(R.drawable.error)
                }
                else{
                    emailcreat_id.setBackgroundResource(R.drawable.noterror)
                }
                  if (jsonobject.getString("msg")=="emptypass" && convert=="not"){
                      Toast.makeText(this, "كلمة المرور فارغة او غير متطابقة", Toast.LENGTH_SHORT).show()
                      password_id.setBackgroundResource(R.drawable.error)
                      convertpass.setBackgroundResource(R.drawable.error)

                  }else{


                  }
            if (jsonobject.getString("msg")=="done") {
                Toast.makeText(this,"تم التسجيل بنجاح  قم بتسجيل الدخول", Toast.LENGTH_SHORT).show()
                var intent=Intent(this,Login::class.java)
                startActivity(intent)
            }else{    }
        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

                var name=user_id.text.toString()
                var lname=user_id2.text.toString()
                var email=emailcreat_id.text.toString()
                var password=password_id.text.toString()
                var convertpass=convertpass.text.toString()





                paramas.put("name",name+" "+lname)
                paramas.put("email",email)
                if (password==convertpass) {
                    paramas.put("password", password)
                }else{
                    paramas.put("password", "")
                    convert="not"


                }







                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }
}
