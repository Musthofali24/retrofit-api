package com.musthofali.retrofitapi

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()
    private lateinit var rvPost: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var tvResponseCode: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvPost = findViewById(R.id.rvPost)
        tvResponseCode = findViewById(R.id.tvResponseCode)

        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        postAdapter = PostAdapter(list)
        rvPost.adapter = postAdapter

        fetchPosts()
    }

    private fun fetchPosts() {
        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching posts: ${t.message}")
            }
        })
    }
}
