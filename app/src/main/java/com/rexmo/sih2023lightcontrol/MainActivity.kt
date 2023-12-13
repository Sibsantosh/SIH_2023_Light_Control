package com.rexmo.sih2023lightcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rexmo.sih2023lightcontrol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        FirebaseDatabase.getInstance().reference.child("/human")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Toast.makeText(this@MainActivity,"human value is ${snapshot.value}",Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        binding.root.setOnClickListener {
            updateValue()
        }
        setContentView(binding.root)
    }


    fun updateValue(){
        FirebaseDatabase.getInstance().reference.child("/light").setValue("123")
            .addOnSuccessListener {
                Toast.makeText(this, "jfdfjsflsdfd", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "${it.message.toString()}", Toast.LENGTH_SHORT).show()
            }
    }
}