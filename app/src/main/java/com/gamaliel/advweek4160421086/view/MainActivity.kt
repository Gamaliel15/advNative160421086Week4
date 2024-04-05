package com.gamaliel.advweek4160421086.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.gamaliel.advweek4160421086.R
import com.gamaliel.advweek4160421086.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.gamaliel.advweek4160421086.util.createNotificationChannel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    init {
        instance = this
    }

    companion object{
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)

            }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)

            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)

                return
            }
            manager.notify(1001, builder.build())

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel."
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("permission_check", "granted")
                //create notif channel
                createNotificationChannel(this,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name), "App notification channel."
                )
            }else{
              Log.d("permission_checked","deny")
            }
            return
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

}