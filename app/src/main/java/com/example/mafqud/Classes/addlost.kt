package com.example.mafqud

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_addlost.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class addlost : AppCompatActivity() {
    lateinit var  image:String
    lateinit  var lastBitmap:Bitmap ;
    var a=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlost)
        //========================chosing Image================

        choseImage.setOnClickListener {

            showFileChooser()
        }


        // ========================= addlostLocation====================

addlocation.setOnClickListener({

   var intint = Intent(this,MapsActivity::class.java)
 var map=1.toString()
    intint.putExtra("map",map)

     startActivityForResult(intint,22)


})

//===========================================================spinner============

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

       // =============================================add new post ======================================

        addpost_id.setOnClickListener(){


            addpost()

        }
    }


    // ========================================= get location ===============================================
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode!=1){

            if (resultCode== Activity.RESULT_OK){
                var location=data!!.getSerializableExtra("location").toString()
                addlocation.setText(location)
                Log.d("location111",location)
            }else{
            }
        }else{


            //==============================================get Image===================================

            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                var filePath = data.getData();
                try {
                    var   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath)
                    lastBitmap = bitmap;
                    a=1
                    // choseImage.setImageBitmap(lastBitmap)
                    image = getStringImage(lastBitmap);
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data)

        }



    }


//=================================================== addpost Function ===========================================
    fun addpost(){



        var shared= getSharedPreferences("idcheck",0)
        var id=shared.getString("id",null)
       // var progres= ProgressDialog.show(this,"الرجاء الانتظار...","يتم الآن إرسال المشاركة",false,false)
        var url= "http://192.168.1.3/mafqud/School/add.php"
    //var url= "http://192.168.1.3/mafqud/add.php"
       // var url="https://flexuous-babies.000webhostapp.com/School/add.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {




                response ->


            var jsonobject= JSONObject(response)

            if (jsonobject.getString("msg")=="title"){

                Toast.makeText(this,"قم بادخال عنوان الموضوع", Toast.LENGTH_SHORT).show()
                titlepost_id.setBackgroundResource(R.drawable.error)
            }else{
                titlepost_id.setBackgroundResource(R.drawable.noterror)

                }

                if (jsonobject.getString("msg") == "det") {
                    Toast.makeText(this, "يجب تعبئة صندوق الوصف ", Toast.LENGTH_SHORT).show()
                    detailspost.setBackgroundResource(R.drawable.error)
                }else{
                    detailspost.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "cat") {

                    Toast.makeText(this, "يجب اختيار الصنف ", Toast.LENGTH_SHORT).show()
                    catpost.setBackgroundResource(R.drawable.error)
                }
                else{
                    catpost.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "phone") {

                    Toast.makeText(this, "الرقم الذي ادخلتة غير صحيح", Toast.LENGTH_SHORT).show()
                    phone_id.setBackgroundResource(R.drawable.error)
                }
                else{
                    phone_id.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "award") {

                    Toast.makeText(this, "يجب ادخال مبلغ المكافئة ", Toast.LENGTH_SHORT).show()
                    award_id.setBackgroundResource(R.drawable.error)
                }
                else{
                    award_id.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "image") {

                    Toast.makeText(this, "يجب اختيار صورة  ", Toast.LENGTH_SHORT).show()
                    choseImage.setBackgroundResource(R.drawable.error)
                }
                else{
                    choseImage.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "status") {

                    Toast.makeText(this, "حدد فيما اذا وجدت او فقدت  ", Toast.LENGTH_SHORT).show()
                    status.setBackgroundResource(R.drawable.error)
                }
                else{
                    status.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg") == "location") {

                    Toast.makeText(this, "حدد بتحديد الموقع", Toast.LENGTH_SHORT).show()
                    addlocation.setBackgroundResource(R.drawable.error)
                }
                else{
                    addlocation.setBackgroundResource(R.drawable.noterror)
                }
                if (jsonobject.getString("msg")=="done") {
                    Toast.makeText(this,"تم النشر بنجاح", Toast.LENGTH_SHORT).show()
                    var intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{    }





        }, Response.ErrorListener {



        }){


            override fun getParams(): MutableMap<String, String> {

                var paramas=HashMap<String,String>()

                var title=titlepost_id.text.toString()
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
                paramas.put("id",id.toString())
                paramas.put("title",title)
                paramas.put("det",det)
                paramas.put("phone",phone)
                paramas.put("award",award)
                paramas.put("dateandtime",dateandtime)
                paramas.put("location",location)
                paramas.put("image",image)








                return paramas


            }





        }

        var requestQueue= Volley.newRequestQueue(this)

        requestQueue.add(stringRequset)


    }

//=================================================== Image Function==========================

    private fun showFileChooser() {
        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageIntent.type = "image/*"
        pickImageIntent.putExtra("aspectX", 1)
        pickImageIntent.putExtra("aspectY", 1)
        pickImageIntent.putExtra("scale", true)
        pickImageIntent.putExtra("outputFormat",
            Bitmap.CompressFormat.JPEG.toString())
        startActivityForResult(pickImageIntent,1)
    }



    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }




}
