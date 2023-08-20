package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.example.contactlistapp.databinding.NewcontactBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import com.example.contactlistapp.Common.Result
import com.example.contactlistapp.Common.gone
import com.example.contactlistapp.Common.show

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


      //  val repository = ImplRepository // Initialize your repository here


        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
binding.vm = viewModel
binding.lifecycleOwner = this





binding.btAddNewContact.setOnClickListener{

    val inflater = LayoutInflater.from(this)
    //val b = inflater.inflate(R.layout.newcontact, null)

    val dialogBinding: NewcontactBinding = DataBindingUtil.inflate(
        inflater,
        R.layout.newcontact,
        null,
        false)
    dialogBinding.vm = viewModel
    dialogBinding.lifecycleOwner = this


    AlertDialog.Builder(this)
        .setView(dialogBinding.root)
        .setPositiveButton("OK") { _, _ ->
           val name = dialogBinding.etName.text.toString()
            val number = dialogBinding.etNumber.text.toString()
            val email = dialogBinding.etEmail.text.toString()


            if (name.isNotEmpty() && number.isNotEmpty() && email.isNotEmpty()){
//viewModel.saveDataList(name, number, email)
          // viewModel.saveData()


                Toast.makeText(this, "Adding contact", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()

            }
//viewModel.name_ = name


        }.setNegativeButton("Cancel") { _, _ ->
            Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
        }
        .create().show()



}

        viewModel.retrieveData()


        binding.contactsRecyclerView.setHasFixedSize(true)
            binding.contactsRecyclerView.layoutManager= LinearLayoutManager(this)

 adapter = ContactProfileAdapter(viewModel.contactProfileData.value?: emptyList())
        binding.contactsRecyclerView.adapter=adapter
        // Observe changes in the contact profile data using LiveData
        viewModel.profileData.observe(this)

       { contactsProfileData ->
           // Update the contact list in the adapter
           adapter.contactProfileData   = listOf(contactsProfileData)
           adapter.notifyDataSetChanged()

        }






}

   /*   private fun initObservers(){


        viewModel.contactProfileData.observe(this){ result ->
            when (result) {
                is Result.Loading->{
                    binding.progressBar.show()
                }
                is Result.Success<*>->{
                   binding.progressBar.gone()
                   binding.contactsRecyclerView.adapter = ContactProfileAdapter(result.result as List<ContactProfileData>)
                }

                is Result.Failure->{
                    binding.progressBar.gone()
                    Snackbar.make(binding.root, result.error, Snackbar.LENGTH_LONG).show()
                }

            }

        }



    }   */
}