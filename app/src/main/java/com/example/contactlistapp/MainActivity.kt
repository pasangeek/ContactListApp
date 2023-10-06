package com.example.contactlistapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.Contact
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.example.contactlistapp.databinding.AddnewcontactBinding
import com.example.contactlistapp.view.ContactProfileAdapter
import com.example.contactlistapp.Data.ContactProfileViewModel
import java.util.Locale

class MainActivity : AppCompatActivity() {
    // Declare view binding, view model, and adapter
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


}



}