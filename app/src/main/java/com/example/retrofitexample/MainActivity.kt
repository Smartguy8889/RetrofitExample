package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.retrofitexample.model.HerosItem
import com.example.retrofitexample.network.superheroApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var listView = findViewById<ListView>(R.id.listView)

            var retrofit = Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();  // Part 1

            val api = retrofit.create(superheroApi::class.java); //Part 2

        api.getHeroes()?.enqueue(object : Callback<List<HerosItem?>?> {
            override fun onResponse(
                call: Call<List<HerosItem?>?>?,
                response: Response<List<HerosItem?>?>
            ) {
                val heroList: List<HerosItem> = (response.body() as List<HerosItem>?)!!
                // Now we can use this heroList for getting the data from API.

                //Creating an String array for the ListView
                val heroes = arrayOfNulls<String>(heroList.size)

                //looping through all the heroes and inserting the names inside the string array
                for (i in heroList.indices) {
                    heroes[i] = heroList[i].name
                    Log.e("test", heroes[0].toString())
                }


                //displaying the string array into listview
                listView.setAdapter(
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        heroes
                    )
                ) //Part Extra
            }

            override fun onFailure(call: Call<List<HerosItem?>?>?, t: Throwable) {
                Toast.makeText(
                    applicationContext, t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) //Part 3

        }
    }