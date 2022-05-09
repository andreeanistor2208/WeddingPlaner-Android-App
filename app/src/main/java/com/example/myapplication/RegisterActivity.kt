package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

//import kotlinx.parcelize.Parcelize

//import kotlinx.android.synthetic.main.activity_register.*

//@Parcelize
class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener {

            if(binding.editEmail.text.isNotEmpty() || binding.editCPassword.text.isNotEmpty() || binding.editPassword.text.isNotEmpty()){
                registerUser();
                //Toast.makeText(this,"Input Provided", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(this,"Input Required", Toast.LENGTH_LONG).show()

            }

        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun registerUser(){

        auth.createUserWithEmailAndPassword(binding.editEmail.text.trim().toString(), binding.editPassword.text.trim().toString())
            .addOnCompleteListener (this) {
                    task ->
                if(task.isSuccessful){

                    Toast.makeText(this,"Register successful",Toast.LENGTH_LONG).show()

                }else{

                    Toast.makeText(this,"Register failed "+task.exception,Toast.LENGTH_LONG).show()


                }
            }


    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser;

        if(user != null){

            val intent = Intent(this, DashboardActivity::class.java);
            startActivity(intent)

        }else{
            Log.e("user status", "User null")
        }
    }
}