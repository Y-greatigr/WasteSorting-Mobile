package com.cookandroid.sorting_mobile

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val imageBitmap = intent.getBundleExtra("imagedata")!!["data"] as Bitmap?
        val pred = intent.getStringExtra("pred")
        val imageView = findViewById<ImageView>(R.id.image)
        imageView.setImageBitmap(imageBitmap)
        val textView = findViewById<TextView>(R.id.label)
        textView.text = pred
    }
}