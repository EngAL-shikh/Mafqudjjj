package com.example.apbarlayout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mafqud.R
import com.example.mafqud.moreditaels
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.posts.view.*

class postsadpter:RecyclerView.Adapter<postsadpter.mViewHolder> {

    var context: Context?=null

    var postslist=ArrayList<Post>()


    constructor(context: Context,mNewslist:ArrayList<Post>){
        this.postslist=mNewslist
        this.context=context


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {


        return mViewHolder(LayoutInflater.from(context).inflate(R.layout.posts,parent,false
        ))
    }

    override fun getItemCount(): Int {

        return postslist.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

       // holder?.idpost.text=postslist.get(position).id.toString()
        holder.idpost.text= postslist.get(position).id.toString()
        holder.title.text=postslist.get(position).title
        holder.details.text=postslist.get(position).description
        holder.publisher.text=postslist.get(position).publisher
        holder.dateandtime.text="تم النشر في |"+postslist.get(position).dateandtime
        holder.tvstatus.text=postslist.get(position).tvstatus
        holder.location.text=postslist.get(position).location

        var a=postslist.get(position).image
        Picasso.get().load("$a").into(holder.image)

       // Log.d("srcimage","${postslist.get(position).image}")
       // holder?.award.text=postslist.get(position).award.toString()
        //holder?.phonepuplsher.text=postslist.get(position).phone.toString()
       // holder?.img.setImageResource(postslist.get(position).img)

       holder.cc.setOnClickListener({

           var i=Intent(context,moreditaels::class.java)
           i.putExtra("id",postslist[position].id.toString())
           i.putExtra("post",postslist[position].description)
           i.putExtra("location",postslist[position].location)
           i.putExtra("title",postslist[position].title)
           i.putExtra("image",postslist[position].image)
           i.putExtra("date",postslist[position].dateandtime)
           i.putExtra("pub",postslist[position].publisher)
           i.putExtra("status",postslist[position].tvstatus)
           i.putExtra("userid",postslist[position].userid)



           (context as Activity).startActivity(i)
       })

      if (holder.tvstatus.text.toString().equals("وجدتة")){


          holder.tvstatus.setBackgroundResource(R.drawable.status)

      }else{

          holder.tvstatus.setBackgroundResource(R.drawable.status2)
      }









    }



    class mViewHolder(view: View): RecyclerView.ViewHolder(view)
    {

        var idpost=view.idpost
        var title=view.title
        var details=view.post
        var publisher=view.publisher
       // var idpost=view.idpost
        var cc =view.cc
        var dateandtime=view.date
        var  tvstatus=view.tvstatus
        var location=view.location
        var image =view.image








    }


}