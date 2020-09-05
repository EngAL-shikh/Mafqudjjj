package com.example.mafqud.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apbarlayout.Post
import com.example.mafqud.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_technology.*
import kotlinx.android.synthetic.main.fragment_technology.view.*
import org.json.JSONObject
import com.example.apbarlayout.postsadpter as postsadpter1

/**
 * A simple [Fragment] subclass.
 */
class technologyFragment : Fragment() {




    fun getdata(view: View){



var posts=ArrayList<Post>()
        //var searchnewslist=ArrayList<Post>()


        var url="https://flexuous-babies.000webhostapp.com/getElect.php"
        //var url="http://192.168.43.191/getElect.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {



                response ->


            Log.d("mresposnse",response)

            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="found"){
                var data=jsonobject.getJSONArray("data")


                for (i in 0..data.length()-1){

                    var jsonObject=data.getJSONObject(i)


                    var post=Post(

                        id= jsonObject.getInt("p_id"),
                        title = jsonObject.getString("title"),
                        description = jsonObject.getString("description"),
                        publisher = jsonObject.getString("uname"),
                        dateandtime = jsonObject.getString("date"),
                        tvstatus = jsonObject.getString("status"),
                        location = jsonObject.getString("location")
                        // phone = jsonObject.getInt("phone"),
                        // award = jsonObject.getInt("award") ,

                    )

                    posts.add(post)




                }




                var costem = postsadpter1(context!!,posts)
                view.rv.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL,false)
                view.rv.adapter=costem









              //  Toast.makeText(context,id+"\n"+title+"\n"+details+"\n"+img, Toast.LENGTH_SHORT).show()
            } else{

                Toast.makeText(context,"not found", Toast.LENGTH_SHORT).show()}



        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

           //     var em=search.text.toString()

             //   paramas.put("email",em)



                return  paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(context)

        requestQueue.add(stringRequset)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view=
            inflater.inflate(R.layout.fragment_technology, container, false)

//        var costem = postsadpter(context!!,postlist)
//        view.rv.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        view.rv.adapter=costem

        getdata(view)


        return view

    }







}
