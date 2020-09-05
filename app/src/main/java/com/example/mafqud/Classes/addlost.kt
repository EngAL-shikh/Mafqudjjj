package com.example.mafqud

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mafqud.fragment.personFragment
import kotlinx.android.synthetic.main.activity_addlost.*
import kotlinx.android.synthetic.main.activity_addlost.name_id
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class addlost : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlost)








addlocation.setOnClickListener({

   var intint = Intent(this,MapsActivity::class.java)
 var map=1.toString()
    intint.putExtra("map",map)

     startActivityForResult(intint,22)


})



        var cats=ArrayList<String>()

        cats.add("الكترونيات")
        cats.add("مستندات")
        cats.add("اشخاص")
        cats.add("حيوانات")


        var adpt=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cats)
        catpost.adapter=adpt

        /// spinner  lost and found

        var catlostfound=ArrayList<String>()

        catlostfound.add("وجدتة")
        catlostfound.add("فقدتة")



        var adpt2=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,catlostfound)
        status.adapter=adpt2


        addpost_id.setOnClickListener(){


            addpost()
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK){


           var location=data!!.getSerializableExtra("location").toString()
            //var loc=  intent.extras!!.getString("location").toString()
            Log.d("amroz","DF")
            addlocation.setText(location)


        }else{


        }

    }



    fun addpost(){



        var url="https://flexuous-babies.000webhostapp.com/add.php"
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

                var title=name_id.text.toString()
                var det=detailspost.text.toString()
               var cat=catpost.selectedItem.toString()
                var phone=phone_id.text.toString()
                var award=award_id.text.toString()
                var status=status.selectedItem.toString()
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val dateandtime = sdf.format(Date())
                val location=addlocation.text.toString()


                if (status=="فقدتة"){

                    var s=status.toString()
                    paramas.put("status",s)
                }else{

                    var s=status.toString()
                    paramas.put("status",s)


                }


                if (cat=="الكترونيات"){
                    var c=1.toString()
                    paramas.put("cat",c) }
                if (cat=="مستندات"){
                    var c=2.toString()
                    paramas.put("cat",c) }
                if (cat=="اشخاص"){
                    var c=3.toString()
                    paramas.put("cat",c) }
                if (cat=="حيوانات"){
                    var c=4.toString()
                    paramas.put("cat",c) }

                paramas.put("title",title)
                paramas.put("det",det)
                paramas.put("phone",phone)
                paramas.put("award",award)
                paramas.put("dateandtime",dateandtime)
                paramas.put("location",location)







                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }



}
