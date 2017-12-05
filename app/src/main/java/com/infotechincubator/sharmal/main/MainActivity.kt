package com.infotechincubator.sharmal.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.infotechincubator.sharmal.AppComponent
import com.infotechincubator.sharmal.R
import com.infotechincubator.sharmal.base.ViewModelActivity
import com.infotechincubator.sharmal.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : ViewModelActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth.signInWithEmailAndPassword("kyawagwin@gmail.com", "32592135").addOnCompleteListener(this, OnCompleteListener<AuthResult> {
            task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBind() {
        super.onBind()

        binding.viewModel = viewModel
    }

    override fun injectDependencies(graph: AppComponent) {
        graph.plus(MainModule(this)).injectTo(this)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
