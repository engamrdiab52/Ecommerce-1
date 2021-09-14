package com.example.ecommercie_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ecommercie_1.MainActivity.Companion.TAG
import com.example.ecommercie_1.databinding.FragmentListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val database = Firebase.database
    val myReference = database.getReference("user_1")
    var mutableList: MutableList<FavoriteOrder> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //    val value = snapshot.getValue(FavoriteOrder::class.java)
                snapshot.children.forEach {
                    val value = snapshot.getValue(FavoriteOrder::class.java)
                    value?.let { it1 -> mutableList.add(it1) }
                    Log.d(TAG, it.value.toString())
                }
                Log.d(TAG, mutableList.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

        })

        return binding.root
    }

}