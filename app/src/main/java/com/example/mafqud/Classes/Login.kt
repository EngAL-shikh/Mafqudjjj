package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_login.setOnClickListener(){

            getdata()


        }

        bt_sinup.setOnClickListener(){

            var txtPassword = findViewById<EditText>(R.id.et_login_pass)
            txtPassword.validator()
                .nonEmpty()
                .atleastOneNumber()
                .atleastOneSpecialCharacters()
                .atleastOneUpperCase()
                .addErrorCallback {
                    txtPassword.error = it
                    // it will contain the right message.
                    // For example, if edit text is empty,
                    // then 'it' will show "Can't be Empty" message
                }
                .check()

            var intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }


    }

    fun getdata(){



        var url="https://flexuous-babies.000webhostapp.com/checklogin.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {



                response ->

            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="found"){



                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this," found", Toast.LENGTH_SHORT).show()
                //Toast.makeText(this,id+"\n"+title+"\n"+details+"\n"+img,Toast.LENGTH_SHORT).show()
            } else{

                Toast.makeText(this,"not found", Toast.LENGTH_SHORT).show()}



        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

                var em=et_login_email.text.toString()
                var pass=et_login_pass.text.toString()

                paramas.put("email",em)
                paramas.put("pass",pass)



                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }
}
