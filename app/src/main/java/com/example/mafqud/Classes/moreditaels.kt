package com.example.mafqud

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_moreditaels.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.posts.*
import org.json.JSONObject

class moreditaels : AppCompatActivity() {
  //  var id= intent.extras!!.getString("id")
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moreditaels)

//========================================= git extras ==========================================

        var id= intent.extras!!.getString("id")
        var userid= intent.extras!!.getInt("userid")
        var title=    intent.extras!!.getString("title").toString()
        idmore.text= intent.extras!!.getString("post").toString()
        titlemore.text=intent.extras!!.getString("title").toString()
        var loc=intent.extras!!.getString("location")
        var image=intent.extras!!.getString("image").toString()
        Picasso.get().load("$image").into(imageidmore)
        dateofpost.text=" تم النشر في  | "+intent.extras!!.getString("date").toString()
        nameogpup.text="اسم المعلن | "+intent.extras!!.getString("pub").toString()
        var status=intent.extras!!.getString("status")

        if (status=="وجدتة"){

            foundit.setText("انه ملكي")
        }

    //================================= Editing MyPOST==============================================
        var shared= getSharedPreferences("idcheck",0)
        var cheack=shared.getString("id",null)

        Log.d("userid",userid.toString())
        if (cheack==userid.toString()){

            EditMyPost.visibility=View.VISIBLE
            rep_foun_location.visibility=View.GONE
        }
        //------------------------------- Reports------------------------------------------
        report.setOnClickListener {

                  if (show_tv_report.visibility==View.GONE){
                      show_tv_report.visibility=View.VISIBLE
                  }else{
                      show_tv_report.visibility=View.GONE


                  }



        }

        bt_sendreport.setOnClickListener {


            report()
            show_tv_report.visibility=View.GONE


        }


        //==============================Deleted-Update-Refrish=================================

        deleted.setOnClickListener {

            delete_update_refrish("del")

        }

      //================================Updating=============================================

      update.setOnClickListener{

          et_update.visibility=View.VISIBLE


      }
      sendupdate.setOnClickListener {

          delete_update_refrish("update")

      }





        //////////////////////////found/////////////////////////////////////////////////////////////

        foundit.setOnClickListener {

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1122334455"))
            startActivity(intent)

//            var i =Intent(Intent.ACTION_CALL,Uri.parse("7787887787"))
//            startActivity(i)
        }



        showlocation.setOnClickListener{

            var intent=Intent(this,MapsActivity::class.java)
            var map =2.toString()
            intent.putExtra("map",map)
            intent.putExtra("location",loc)
            intent.putExtra("title",title)
            startActivity(intent)
        }


    }
//==========================================Function=======================================

    //=======reports===============================
    fun report(){


        var url= "http://192.168.1.3/mafqud/report.php"
        //var url="https://flexuous-babies.000webhostapp.com/adduser.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {




                response ->


            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="done") {
                Toast.makeText(this,"شكرا سوف يتم مراجعة بلاغك ", Toast.LENGTH_SHORT).show()

            }else{  Toast.makeText(this,"عذرا هناك مشكلة ", Toast.LENGTH_SHORT).show()  }
        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

                var idreport=intent.extras!!.getString("id").toString()


              var reportM=tv_report.text


                paramas.put("reportM",reportM.toString())
                paramas.put("id",idreport)

                Log.d("rep",reportM.toString())







                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }

    //=======Deleted===============================

    fun delete_update_refrish(Obretion:String){


        var url= "http://192.168.1.3/mafqud/update_My_post.php"
        //var url="https://flexuous-babies.000webhostapp.com/adduser.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {




                response ->


            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="done1") {
                Toast.makeText(this,"تمت الحذف بنجاح ", Toast.LENGTH_SHORT).show()
                var Intent=Intent(this,MyPosts::class.java)
                startActivity(Intent)

            }

            if (jsonobject.getString("msg")=="newtitle_empty") {
                Toast.makeText(this,"قم بأدخل الاسم  ", Toast.LENGTH_SHORT).show()


            }

            if (jsonobject.getString("msg")=="newd_empty") {
                Toast.makeText(this,"قم بتعبئةصندوق الوصف ", Toast.LENGTH_SHORT).show()


            }

            if (jsonobject.getString("msg")=="done2") {
                Toast.makeText(this,"تم التعديل بنجاح ", Toast.LENGTH_SHORT).show()
                var Intent=Intent(this,MyPosts::class.java)
                startActivity(Intent)

            }



        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()



                var id=intent.extras!!.getString("id")
                var newtitle=newtitle.text.toString()
                var newd=newdescrption.text.toString()




                if (Obretion=="del"){

                    paramas.put("id",id.toString())
                    paramas.put("del","del")


                }else{
                    paramas.put("del","update")
                    paramas.put("id",id.toString())
                    paramas.put("newtitle",newtitle)
                    paramas.put("newd",newd)
                }











                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }
}
