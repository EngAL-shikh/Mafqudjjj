package com.example.apbarlayout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mafqud.MainActivity
import com.example.mafqud.R
import com.example.mafqud.moreditaels
import kotlinx.android.synthetic.main.activity_addlost.view.*
import kotlinx.android.synthetic.main.posts.*

import kotlinx.android.synthetic.main.posts.view.*

class MyPostAdpter:RecyclerView.Adapter<MyPostAdpter.mViewHolder> {

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
        holder.title.text=postslist.get(position).title
        holder.details.text=postslist.get(position).description
        holder.publisher.text=postslist.get(position).publisher
        holder.dateandtime.text=postslist.get(position).dateandtime
        holder.tvstatus.text=postslist.get(position).tvstatus
        holder.location.text=postslist.get(position).location
       // holder?.award.text=postslist.get(position).award.toString()
        //holder?.phonepuplsher.text=postslist.get(position).phone.toString()
       // holder?.img.setImageResource(postslist.get(position).img)

       holder.cc.setOnClickListener({

           var i=Intent(context,moreditaels::class.java)
           i.putExtra("post",postslist[position].description)
           i.putExtra("location",postslist[position].location)
           i.putExtra("title",postslist[position].title)
           (context as Activity).startActivity(i)
       })

      if (holder.tvstatus.text.toString().equals("وجدتة")){


          holder.tvstatus.setBackgroundColor(Color.GREEN)

      }else{

          holder.tvstatus.setBackgroundColor(Color.RED)
      }









    }



    class mViewHolder(view: View): RecyclerView.ViewHolder(view)
    {

        var title=view.title
        var details=view.post
        var publisher=view.publisher
       // var idpost=view.idpost
        var cc =view.cc
        var dateandtime=view.date
        var  tvstatus=view.tvstatus
        var location=view.location








    }


}