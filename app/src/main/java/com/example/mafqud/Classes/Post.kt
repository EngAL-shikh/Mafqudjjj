package com.example.apbarlayout

data class  Post(
    var location:String,
   var id:Int,
    var title:String,
    var description:String,
    var publisher:String,
    val dateandtime:String,
   var tvstatus:String,
    var image:String,
    var userid:Int
   // var phone: Int,
   // var award: Int
)  {
}

data class MyPost(var id:Int){}