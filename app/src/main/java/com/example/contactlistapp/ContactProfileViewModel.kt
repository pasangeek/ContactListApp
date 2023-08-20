package com.example.contactlistapp


import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactlistapp.Data.ContactProfileData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactProfileViewModel@Inject constructor(private val implRepository: ImplRepository) : ViewModel(){
    private val _contactProfileDataList = MutableLiveData<List<ContactProfileData>>()
   val contactProfileData: LiveData<List<ContactProfileData>> = _contactProfileDataList

    var name_: MutableLiveData<String> = MutableLiveData("")


    var number_: MutableLiveData<String> = MutableLiveData("")


    var email_: MutableLiveData<String> = MutableLiveData("")



var profileData : MutableLiveData<ContactProfileData> = MutableLiveData()

  /*  fun updateContactProfileList(newList: List<ContactProfileData>) {

       // _contactProfileDataList.value = newList
    }*/
  fun saveData(){
      viewModelScope.launch (Dispatchers.IO){

          implRepository.saveContact(
              ContactProfileData(

                  name = name_.value!!,
                  number =number_.value!!,
                  email = email_.value!!

              )


          )
      }

  }
    fun retrieveData(){

        viewModelScope.launch (Dispatchers.IO){
            implRepository.getContact().collect{

                profileData.postValue(it)
            }
        }
    }




}
