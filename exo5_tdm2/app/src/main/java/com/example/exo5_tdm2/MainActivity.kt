package com.example.exo5_tdm2

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
val REQUEST_PERMESSION=1
    val REQUEST_PERMESSION2=2
    var test2:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val test=findViewById<TextView>(R.id.test)
        val btn=findViewById<Button>(R.id.btn)
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_CONTACTS),REQUEST_PERMESSION)
        }
        else {

             test2=getContacts()
            test.text=test2.toString()
            Log.d("test",test2)
            Log.w("test",test2)
            btn.setOnClickListener{
                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.SEND_SMS),REQUEST_PERMESSION2)
                }
                else{
                    sendSms(test2)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==REQUEST_PERMESSION){
          getContacts()


        }
        if(requestCode== REQUEST_PERMESSION2){
            sendSms(this.test2)
        }
    }
    private fun sendSms(number:String){
        SmsManager.getDefault().sendTextMessage("0796300359",null,"test",null,null)

    }
    private fun  getContacts() :String{
        var phoneNumber =""
        val contactCurser=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        if ((contactCurser?.count?:0)>0){
            while (contactCurser!=null && contactCurser.moveToNext()){
                val rowID=contactCurser.getString(contactCurser.getColumnIndex(ContactsContract.Contacts._ID))

                if (contactCurser.getInt(contactCurser.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0){
                    val phoneNumberCurser=contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?",
                        arrayOf<String>(rowID),
                        null
                    )
                    if (phoneNumberCurser != null){
                    while (phoneNumberCurser.moveToNext()){
                        phoneNumber= phoneNumberCurser.getString(
                            phoneNumberCurser.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )+"\n"


                    }
                        phoneNumberCurser.close()
                    }

                }

            }

        }
        return phoneNumber
    }
}
