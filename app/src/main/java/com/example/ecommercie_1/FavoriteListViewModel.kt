package com.example.ecommercie_1

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FavoriteListViewModel : ViewModel() {
    /*val firebaseStorage = Firebase.storage
    val storageReference = firebaseStorage.reference
    val imageReference = storageReference.child("bags/bag_1.jbg")
*/
    private val favoriteOrder_mutable = MutableLiveData<List<FavoriteOrder>>()
    val favoriteOrder: LiveData<List<FavoriteOrder>>
        get() = favoriteOrder_mutable
    private val database = Firebase.database
    val myReference = database.getReference("user_1")
    val mutableList: MutableList<FavoriteOrder> = mutableListOf()


    fun startDownload() {
        viewModelScope.launch {
            downloadFavorites()
        }
    }

    private suspend fun downloadFavorites() {
        try {
            val snapshot: DataSnapshot = myReference.get().await()
            snapshot.children.forEach {
                val value = it.getValue(FavoriteOrder::class.java)
                value?.let { it1 -> mutableList.add(it1) }
            }
            favoriteOrder_mutable.postValue(mutableList)
            Log.d(MainActivity.TAG, mutableList.toString())
        } catch (ex: Exception) {
            Log.d(MainActivity.TAG, ex.toString())
        }

    }
}