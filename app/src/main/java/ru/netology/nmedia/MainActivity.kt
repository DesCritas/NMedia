package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter()
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts

        }

        adapter.onLikeListener = {
            viewModel.likeById(it.id)
        }
        adapter.onShareListener = {
            viewModel.shareById(it.id)
        }


    }


}