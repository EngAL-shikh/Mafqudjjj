package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apbarlayout.MyPost
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_login.*
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

        var mypost=ArrayList<MyPost>()
        var url= "http://192.168.1.3/mafqud/checklogin.php"
        //var url="https://flexuous-babies.000webhostapp.com/checklogin.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {
                response ->
            var jsonobject= JSONObject(response)
            if (jsonobject.getString("msg")=="found"){
                var data=jsonobject.getJSONArray("data")
                for (i in 0..data.length()-1){
                    var jsonObject=data.getJSONObject(i)

                    var id=jsonObject.getInt("id").toString()
                    Log.d("printid",id)
                    var shared= getSharedPreferences("idcheck",0)
                    var edit=shared.edit()
                    edit.putString("id",id)
                    edit.commit()
                }
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            } else{

                Toast.makeText(this,"تأكد من االايميل و كلمة المرور", Toast.LENGTH_SHORT).show()}
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
