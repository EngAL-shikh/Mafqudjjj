package com.example.mafqud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apbarlayout.Post
import com.example.apbarlayout.postsadpter
import kotlinx.android.synthetic.main.activity_my_posts.*
import kotlinx.android.synthetic.main.fragment_technology.view.*
import org.json.JSONObject

class MyPosts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_posts)

        //----------------------------shaerdP-----------------




        getMyPost()


    }

    fun getMyPost(){



        var posts=ArrayList<Post>()
        //var searchnewslist=ArrayList<Post>()

        var url= "http://192.168.1.3/mafqud/getMyPost.php"
       // var url="https://flexuous-babies.000webhostapp.com/getMyPost.php"
        //var url="http://192.168.43.191/getElect.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {



                response ->


            Log.d("mresposnse",response)

            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="found"){
                var data=jsonobject.getJSONArray("data")


                for (i in 0..data.length()-1){

                    var jsonObject=data.getJSONObject(i)


                    var post= Post(

                        id= jsonObject.getInt("p_id"),
                        title = jsonObject.getString("title"),
                        description = jsonObject.getString("description"),
                        publisher = jsonObject.getString("uname"),
                        dateandtime = jsonObject.getString("date"),
                        tvstatus = jsonObject.getString("status"),
                        location = jsonObject.getString("location"),
                        image=jsonObject.getString("image"),
                        userid=jsonObject.getInt("userid")
                        // phone = jsonObject.getInt("phone"),
                        // award = jsonObject.getInt("award") ,

                    )

                    posts.add(post)




                }




                var costem = postsadpter(this!!,posts)
                rvMypost.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
                rvMypost.adapter=costem









                //  Toast.makeText(context,id+"\n"+title+"\n"+details+"\n"+img, Toast.LENGTH_SHORT).show()
            } else{

                Toast.makeText(this,"ليس لديك منشورات حتى الان", Toast.LENGTH_SHORT).show()}



        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()


                var shared= getSharedPreferences("idcheck",0)
                var id=shared.getString("id",null)
                   paramas.put("id",id.toString())
                   Log.d("getid",id)



                return  paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }
}
