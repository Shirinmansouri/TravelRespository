package com.example.group6_mapd711_assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class FirebaseFragment : Fragment() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firebase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shared =  this.activity?.getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
        val username = shared?.getString("UserName", "")
        if (username == "admin")
        {
            database = Firebase.database.reference
            database.child("CruiseDatabase").child("Logs")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            view.findViewById<TextView>(R.id.logTexViewFirebase).text =
                                view.findViewById<TextView>(R.id.logTexViewFirebase).text.toString() + "\n " + postSnapshot.value.toString()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        // Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                })
        }
    else
    {
        view.findViewById<TextView>(R.id.logTexViewFirebase).text = "User doesn't have permission to see the logs!"
    }

}
}