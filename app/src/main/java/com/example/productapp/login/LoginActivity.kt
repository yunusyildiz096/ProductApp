package com.example.productapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.productapp.MainActivity
import com.example.productapp.R
import com.example.productsapp.adapters.LoginAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: LoginAdapter
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.view_pager)
        adapter = LoginAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter

        auth = Firebase.auth

        TabLayoutMediator(tabLayout,viewPager2){ tab ,position ->
            when(position){
                0 -> {
                    tab.text = "Sing Up"
                }
                1 ->{
                    tab.text = "Sing In"
                }
            }

        }.attach()

        if (auth.currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}