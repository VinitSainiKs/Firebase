package com.example.firebase.utils

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebase.model.UserDetails
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object Repository {

    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun createUser(email: String, password: String) {
        val userAuth = FirebaseAuth.getInstance()
        userAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { Task ->
            if (Task.isSuccessful) {
                println("User Created Successfully")
                userAuth.signOut()
                firebaseAuth.signInWithEmailAndPassword("karansainivs@gmail.com", "123456")
                    .addOnCompleteListener {
                        if (it.isSuccessful)
                            println("karansainivs@gmail.com is loggedIn successfully")
                        else
                            println("Failed -->> ${it.exception?.message}")
                    }
            } else {
                println("Failed in creating user -->> ${Task.exception?.message}")
                println("Failed  -->> ${Task.exception?.localizedMessage}")
            }
        }
    }

    fun insertUserDetails(userDetails: UserDetails, mobile: String): Task<Void> {
        return firebaseDatabase.getReference("user_details/$mobile").setValue(userDetails)
    }

    fun getUserList(): MutableLiveData<List<UserDetails>> {
        val userDetailsMLD = MutableLiveData<List<UserDetails>>()
        firebaseDatabase.getReference("user_details")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    println("Message -->> ${p0.message}")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val userDetailsList = arrayListOf<UserDetails>()
                    p0.children.forEach {
                        val userDetails = it.getValue(UserDetails::class.java)
                        userDetailsList.add(userDetails!!)
                    }
                    userDetailsMLD.value = userDetailsList
                }

            })
        return userDetailsMLD
    }


}