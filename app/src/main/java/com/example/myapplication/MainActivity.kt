package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

//import kotlinx.android.synthetic.main.activity_main.*


/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 *
 * val manager = PeripheralManager.getInstance()
 * val gpio = manager.openGpio("BCM6").apply {
 *     setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * }
 * gpio.value = true
 *
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        binding.buttonLogin.setOnClickListener() {
//            val intent = Intent(this, TowwActivity::class.java)
//            intent.putExtra("data", "test data")
//            startActivity(intent)
            if(binding.edUsername.text.trim().isNotEmpty() || binding.edPassword.text.trim().isNotEmpty()){
                //Toast.makeText(this,"Input provided",Toast.LENGTH_LONG).show()
                signInUser();

            }else{
                Toast.makeText(this,"Input required",Toast.LENGTH_LONG).show()

            }


            binding.tvRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java);
                startActivity(intent)
            }


        }
    }

    fun signInUser(){
        auth.signInWithEmailAndPassword(binding.edUsername.text.trim().toString(),binding.edPassword.text.trim().toString())
            .addOnCompleteListener (this) {
                    task ->
                if(task.isSuccessful){
                    val intent = Intent(this,DashboardActivity::class.java);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Authentication Error "+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;
//        if(user != null){
//            val intent = Intent(this,DashboardActivity::class.java);
//            startActivity(intent)
//
//        }else{
//            Toast.makeText(this,"User first time login",Toast.LENGTH_LONG).show()
//        }
    }
}
