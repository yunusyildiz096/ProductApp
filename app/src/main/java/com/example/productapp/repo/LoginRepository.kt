package com.example.productsapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.productsapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginRepository(application: Application) {

    private var auth : FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    var userInfo = MutableLiveData<User>()

    fun signUp(email : String,password : String,userName : String){

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

                    val current = auth.currentUser
                    current.let { fbUser ->

                        val user = hashMapOf<String,Any>()
                        user.put("email",auth.currentUser!!.email!!)
                        user.put("username",userName)

                        db.collection("user").document(fbUser!!.uid).set(user).addOnSuccessListener {

                        }
                    }
            }.addOnFailureListener {

            }

        }

    fun signIn(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

        }.addOnFailureListener {

        }

       }

    fun getUser(){

        auth.currentUser?.let { user ->
            val docRef = db.collection("user").document(user.uid)
            docRef.get().addOnSuccessListener { document ->
                document.let {
                    userInfo.value = User(
                        document.get("username") as? String,
                        user.email)
                }

            }.addOnFailureListener {

            }
        }
    }

    fun signOut(){
        auth.signOut()
    }

    }
