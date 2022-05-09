package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityTowwBinding
//import kotlinx.android.synthetic.main.activity_toww.*
import java.util.*


class TowwActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTowwBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toww)

        binding = ActivityTowwBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val passedData : String = intent.getStringExtra("data").toString();

        binding.tvSecond.setText(passedData);

        Log.e("passed data", passedData)

    }
}