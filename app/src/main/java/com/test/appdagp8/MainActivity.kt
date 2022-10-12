package com.test.appdagp8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.appdfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
    }
}